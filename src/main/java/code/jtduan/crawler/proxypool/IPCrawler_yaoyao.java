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
public class IPCrawler_yaoyao extends IPRegexCrawler{
    public IPCrawler_yaoyao() {
    }

    private int cur = -1;

    private String[] url =new String[]{"http://www.httpsdaili.com/free.asp?stype=1&page=","http://www.httpsdaili.com/free.asp?stype=1&page="};

    public IPCrawler_yaoyao(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        cur++;
        if(cur>=6) return "";
        String next = url[cur%2]+(cur/2+1);
        return next;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td.*?>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>\\W*{0,10}?<td.*?>(\\d{1,5})</td>";
    }

    public static void main(String[] args) {
        new IPCrawler_yaoyao(new SimpleHtmlGeter()).start();
    }
}
