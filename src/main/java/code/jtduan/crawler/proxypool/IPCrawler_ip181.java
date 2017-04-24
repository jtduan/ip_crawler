package code.jtduan.crawler.proxypool;

import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SimpleHtmlGeter;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 不分城市
 * 国内的较少
 */
@Component
public class IPCrawler_ip181 extends IPRegexCrawler{

    String url="http://www.ip181.com";
    int cur_page=-1;

    public IPCrawler_ip181(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    public IPCrawler_ip181() {
        super(new SimpleHtmlGeter());
    }

    @Override
    public String getNextUrl() {
        if(cur_page>=2) return "";
        cur_page++;
        if(cur_page==0){
            return url;
        }
        else{
            return url+"/daili/"+cur_page+".html";
        }
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{1,10}?<td>(\\d{1,5})</td>";
    }

    public static void main(String[] args) {
        new IPCrawler_ip181().start();
    }
}
