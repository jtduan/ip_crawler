package code.jtduan.crawler.proxypool;


import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SimpleHtmlGeter;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 可以分城市
 * 质量一般
 */
@Component
public class IPCrawler_ip3366 extends IPRegexCrawler{
    public IPCrawler_ip3366() {
    }

    private int cur = 0;
    private String[] url =new String[]{"http://www.ip3366.net/free/?stype=2&page=","http://www.ip3366.net/free/?stype=2&page="};

    public IPCrawler_ip3366(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(cur>=4) return "";
        String next = url[cur%2]+(cur/2+1);
        cur++;
        return next;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td.*?>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{1,10}?<td.*?>(\\d{1,5})</td>";
    }

    public static void main(String[] args) {
        new IPCrawler_ip3366(new SimpleHtmlGeter()).start();
    }
}
