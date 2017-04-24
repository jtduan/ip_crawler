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
public class IPCrawler_baizhongsu extends IPRegexCrawler {
    public IPCrawler_baizhongsu() {
    }

    private int cur = 0;

    private String[] url = new String[]{"http://ip.baizhongsou.com/default.aspx"};

    public IPCrawler_baizhongsu(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if (cur == 0) {
            cur = 1;
            return url[0];
        }
        return "";
    }

    @Override
    protected String getIPRegex() {
        return "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})";
    }

    public static void main(String[] args) {
        new IPCrawler_baizhongsu(new SimpleHtmlGeter()).start();
    }
}
