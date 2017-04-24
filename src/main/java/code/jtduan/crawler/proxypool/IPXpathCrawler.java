package code.jtduan.crawler.proxypool;

import java.util.ArrayList;
import java.util.List;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SimpleHtmlGeter;
import code.jtduan.util.XDocument;

/**
 * Created by jintaoduan on 17/4/22.
 */
public abstract class IPXpathCrawler extends IPCrawler{
    protected HtmlGeter htmlGeter;
    private static final String IPRegex = "\\b(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\b";

    public IPXpathCrawler() {
        this.htmlGeter = new SimpleHtmlGeter();
    }

    public IPXpathCrawler(HtmlGeter htmlGeter) {
        this.htmlGeter = htmlGeter;
    }

    public List<IP> crawl(String url) {
        List<IP> res = new ArrayList<>();
        String html = htmlGeter.getHtml(url);

        XDocument doc = new XDocument(html);
        try {
            List<Object> ips = doc.sel(getIPXpath());
            List<Object> ports = doc.sel(getPortXpath());
            for(int i=0;i<ips.size();i++){
                res.add(new IP(getIP((String)ips.get(i)),getPort((String)ports.get(i))));
            }
        } catch (XpathSyntaxErrorException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    protected String getIP(String s){
        return s;
    }

    private String getPort(String port) {
        return port;
    }

    protected abstract String getPortXpath();

    protected abstract String getIPXpath();
}
