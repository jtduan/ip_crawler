package code.jtduan.crawler.proxypool;

import code.jtduan.crawler.IPCheck;
import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SimpleHtmlGeter;
import java.util.List;

/**
 * Created by jintaoduan on 17/4/21.
 */
public abstract class IPCrawler {

    protected HtmlGeter htmlGeter;

    public IPCrawler() {
        this.htmlGeter = new SimpleHtmlGeter();
    }

    public IPCrawler(HtmlGeter htmlGeter) {
        this.htmlGeter = htmlGeter;
    }

    public void start(){
        String next = getNextUrl();
        while(!next.isEmpty()) {
            List<IP> ips = crawl(next);
            for(IP ip:ips) {
                IPCheck.check(ip,getSource());
            }
            next = getNextUrl();
        }
    }

    protected abstract List<IP> crawl(String next);

    public abstract String getNextUrl();

    public String getSource(){
        return this.getClass().getSimpleName();
    }
}
