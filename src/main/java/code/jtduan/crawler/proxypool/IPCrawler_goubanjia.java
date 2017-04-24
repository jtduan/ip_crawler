package code.jtduan.crawler.proxypool;

import java.util.ArrayList;
import java.util.List;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import code.jtduan.crawler.proxypool.html.SeleniumGeter;
import code.jtduan.util.StringUtil;
import code.jtduan.util.XDocument;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 不可分城市
 * 代理质量较差
 */
@Component
public class IPCrawler_goubanjia extends IPCrawler {

    private int cur = 0;

    private String[] url = new String[]{"http://www.goubanjia.com/free/gngn/index", "http://www.goubanjia.com/free/gnpt/index"};

    public IPCrawler_goubanjia() {
        super(new SeleniumGeter());
    }

    @Override
    public List<IP> crawl(String url) {
        List<IP> res = new ArrayList<>();
        String html = super.htmlGeter.getHtml(url);

        XDocument doc = new XDocument(html);
        try {
            List<Object> ips = doc.sel("//td[@class='ip']/child::node()");
            for (Object obj : ips) {
                String str = (String) obj;
                str = str.replaceAll("<(\\w{1,4})[^</>]*?none[^</>]*?>.*?</\\1>", "");
                str = str.replaceAll("<.*?>", "").replaceAll("[^0-9.:]","");
                String ip = StringUtil.getMatch(str,"\\b(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\b");
                String port = StringUtil.getMatch(str,":(\\d{1,5})\\b");
                res.add(new IP(ip,port));
            }
        } catch (XpathSyntaxErrorException e) {
        }
        return res;
    }

    @Override
    public String getNextUrl() {
        if (cur >= 1) return "";
        String next = url[cur % 2] + (cur / 2 + 1) + ".shtml";
        cur++;
        return next;
    }

    public static void main(String[] args) {
        new IPCrawler_goubanjia().start();
    }
}
