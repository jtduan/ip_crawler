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
public class IPCrawler_Kuai extends IPRegexCrawler{

    private int cur = 0;

    public IPCrawler_Kuai() {
    }

    private String[] url =new String[]{"http://www.kuaidaili.com/free/intr/","http://www.kuaidaili.com/free/inha/"};

    public IPCrawler_Kuai(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(cur>=10) return "";
        String next = url[cur%2]+(cur/2+1);
        cur++;
        return next;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td.*?>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{1,10}?<td.*?>(\\d{1,5})</td>";
    }

    public static void main(String[] args) {
        new IPCrawler_Kuai(new SimpleHtmlGeter()).start();
    }
}
