package code.jtduan.crawler;

import code.jtduan.util.StarUtil;
import code.jtduan.crawler.proxypool.IP;
import code.jtduan.service.IPService;
import code.jtduan.util.FileUtil;
import code.jtduan.util.StringUtil;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 */
@Component
public class IPCheck {

    private static Logger logger = LoggerFactory.getLogger(IPCheck.class);

    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(300);
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 50, 20, TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());

    public static void check(IP ip, String source) {
        executor.execute(new CheckIpThread(ip, source));
    }

    public static void checkIPFromFile(String file) {
        String lines = FileUtil.readFileByLines(file);
        for (String line : lines.split(System.getProperty("line.separator"))) {
            String ip = StringUtil.getMatch(line, "(\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3})");
            String port = StringUtil.getMatch(line, ":(\\d{1,5})\\b");
            executor.execute(new CheckIpThread(new IP(ip, port), "file"));
        }
    }

    public static void main(String[] args) {
        IPCheck.checkIPFromFile("vaildIp.txt");
//        IPCheck.check(new IP("42.157.7.43","9999"));
    }

    static class CheckIpThread implements Runnable {
        private IP ip;
        private String source;
        private int[] ports = new int[] { 8080, 808, 80, 3128, 9999, 9997 };

        public CheckIpThread(IP ip, String source) {
            this.ip = ip;
            this.source = source;
        }

        @Override
        public void run() {
            if (ip.getPort() <= 0) {
                for (int port : ports) {
                    if (checkHost(ip.getIp(), port)) {
                        checkProxyIp(ip.getIp(), port, source);
                        return;
                    }
                }
                System.out.println("不可用：" + ip.getIp() + ":-1");
            } else {
                if (checkHost(ip.getIp(), ip.getPort())) {
                    checkProxyIp(ip.getIp(), ip.getPort(), source);
                }
            }
        }


        /**
         * 批量代理IP有效检测
         */
        private static void checkProxyIp(String ip, int port, String source) {
            try {
                //Proxy类代理方法
                URL url = null;
                url = new URL("http://www.baidu.com/s?wd=ip");
                // 创建代理服务器
                InetSocketAddress addr = null;
                addr = new InetSocketAddress(ip, port);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
                URLConnection conn = url.openConnection(proxy);
                conn.setConnectTimeout(10 * 1000);
                conn.setReadTimeout(10 * 1000);
                InputStream in = conn.getInputStream();
                String s = IOUtils.toString(in, "UTF-8");
                //System.out.println(s);
                if (s.indexOf("我的ip地址") > 0) {
                    Pattern p = Pattern.compile("我的ip地址.*?属于(.*?)。");
                    Matcher matcher = p.matcher(s);
                    if (matcher.find()) {
                        if (IPService.add(ip, port, source, matcher.group(1))) {
                            System.out.println("可用：" + ip + ":" + port + ";" + matcher.group(1)
                                    + ":结果" + StarUtil.testStar2(proxy));
                        }
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }

        private static boolean checkHost(String ip, int port) {
            try {
                Socket s = new Socket();
                SocketAddress socketAddress = new InetSocketAddress(ip, port);
                s.connect(socketAddress, 5 * 1000);
                s.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public static void main(String[] args) throws Exception {
            System.out.println(checkHost("124.128.221.27", 8080));
        }
    }
}
