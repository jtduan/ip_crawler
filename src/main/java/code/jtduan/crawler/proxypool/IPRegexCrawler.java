package code.jtduan.crawler.proxypool;

import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;


/**
 * Created by jintaoduan on 17/4/22.
 */
public abstract class IPRegexCrawler extends IPCrawler{

    org.slf4j.Logger logger= LoggerFactory.getLogger(IPRegexCrawler.class);

    public IPRegexCrawler() {
    }

    public IPRegexCrawler(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    public List<IP> crawl(String url) {
        List<IP> res = new ArrayList<>();
        String html = htmlGeter.getHtml(url);
        html = StringUtil.decodeUnicode(html);
        Pattern p = Pattern.compile(getIPRegex());
        Matcher m = p.matcher(html);
        while (m.find()) {
            IP ip = new IP(m.group(1), m.group(2));
            res.add(ip);
        }
        if(res.isEmpty()){
            logger.warn("抓取到空内容"+url);
        }
        return res;
    }

    protected abstract String getIPRegex();
}
