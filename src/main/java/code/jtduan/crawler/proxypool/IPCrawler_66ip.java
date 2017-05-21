package code.jtduan.crawler.proxypool;

import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SeleniumGeter;
import code.jtduan.crawler.proxypool.html.SimpleHtmlGeter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 不可分城市(精确到省)
 * 代理质量较差
 */
@Component
public class IPCrawler_66ip extends IPRegexCrawler implements CityIPCrawler{

    public IPCrawler_66ip() {
    }

    private int cur = 0;

    private String[] url =new String[]{"http://www.66ip.cn/mo.php?sxb=%B1%B1%BE%A9&tqsl=1000&port=&export=&ktip=&sxa=&submit=%CC%E1++%C8%A1&textarea="};

    public IPCrawler_66ip(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(cur==0){
            cur=1;
            return url[0];
        }
        return "";
    }

    @Override
    protected String getIPRegex() {
        return "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})";
    }

//    public static void main(String[] args) {
//        List list = new IPCrawler_66ip(new SimpleHtmlGeter()).getIP("","南阳");
//        System.out.println(list.size());
//    }

    @Override
    public List<IP> getIP(String provinceName, String cityName) {
        String url = null;
        try {
            url = "http://www.66ip.cn/mo.php?sxb="+ URLEncoder.encode(cityName,"GBK");
            super.htmlGeter= new SeleniumGeter();
            return crawl(url);
        } catch (UnsupportedEncodingException e) {
            return Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        new IPCrawler_66ip().start();
    }
}
