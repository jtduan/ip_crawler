package code.jtduan.crawler.proxypool;

import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SimpleHtmlGeter;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 不可分城市
 * 代理质量较差
 */
@Component
public class IPCrawler_mayi extends IPRegexCrawler implements CityIPCrawler{
    public IPCrawler_mayi() {
    }

    private int cur = 0;

    private String[] url = new String[]{"http://www.mayidaili.com/free/location/中国-2-1814991/"};

    public IPCrawler_mayi(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(cur>=5) return "";
        cur++;
        return url[0] + cur;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\W{1,10}</td>(.)";
    }

    @Override
    public List<IP> crawl(String url) {
        List<IP> list = super.crawl(url);
        for (IP ip : list) {
            ip.setPort("-1");
        }
        return list;
    }

    public static void main(String[] args) {
        new IPCrawler_mayi(new SimpleHtmlGeter()).start();
    }

    @Override
    public List<IP> getIP(String provinceName, String cityName) {
        return Collections.emptyList();
    }
}
