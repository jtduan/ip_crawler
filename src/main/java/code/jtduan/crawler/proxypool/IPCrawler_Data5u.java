package code.jtduan.crawler.proxypool;

import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SeleniumGeter;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 */
@Component
public class IPCrawler_Data5u extends IPRegexCrawler implements CityIPCrawler{
    private int cur = 0;

    public IPCrawler_Data5u() {
        super(new SeleniumGeter());
    }

    private String[] url =new String[]{"http://www.data5u.com/free/gnpt/index.shtml","http://www.data5u.com/free/area/%E4%BD%9B%E5%B1%B1%E5%B8%82/index.html"};

    public IPCrawler_Data5u(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(cur>=2) return "";
        String next = url[cur];
        cur++;
        return next;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<li>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</li>.*?<li.*?>(\\d{1,5})</li>";
    }

    public static void main(String[] args) {
        List list = new IPCrawler_Data5u(new SeleniumGeter()).getIP("","钦州市");
        System.out.println(list.size());
    }

    @Override
    public List<IP> getIP(String provinceName,String cityName) {
        if(!cityName.endsWith("市")){
            cityName = cityName+"市";
        }
        String url = "http://www.data5u.com/free/area/"+ URLEncoder.encode(cityName)+"/index.html";
        return crawl(url);
    }
}
