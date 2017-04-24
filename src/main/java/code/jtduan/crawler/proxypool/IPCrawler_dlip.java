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
public class IPCrawler_dlip extends IPRegexCrawler{

    private int cur = -1;

    public IPCrawler_dlip() {
    }

    private String[] url =new String[]{"http://www.dlip.cn/gnp/","http://www.dlip.cn/gng/"};

    public IPCrawler_dlip(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(cur>=5) return "";
        cur++;
        if(cur<=1) return url[cur];
        else{
            String next = url[cur%2]+"index_"+(cur/2+1) +".html";
            return next;
        }
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{0,10}?<td>(\\d{1,5})</td>";
    }

    public static void main(String[] args) {
        new IPCrawler_dlip(new SimpleHtmlGeter()).start();
    }
}
