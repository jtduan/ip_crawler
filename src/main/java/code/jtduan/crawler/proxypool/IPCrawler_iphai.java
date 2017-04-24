package code.jtduan.crawler.proxypool;


import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SimpleHtmlGeter;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 不可分城市
 * 代理质量较差
 */
@Component
public class IPCrawler_iphai extends IPRegexCrawler{
    public IPCrawler_iphai() {
    }

    private int cur = 0;

    private String[] url =new String[]{"http://www.iphai.com/","http://www.iphai.com/free/ng","http://www.iphai.com/free/np"};

    public IPCrawler_iphai(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(cur>=3) return "";
        return url[cur++];
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>\\W*?(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\W*?</td>\\W{1,10}?<td>\\W*?(\\d{1,5})\\W*?</td>";
    }

    public static void main(String[] args) {
        new IPCrawler_iphai(new SimpleHtmlGeter()).start();
    }
}
