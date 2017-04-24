package code.jtduan.crawler.proxypool;

import java.util.List;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 极低概率可用
 */
@Component
public class IPCrawler_xsdaili extends IPRegexCrawler implements CityIPCrawler{
    public IPCrawler_xsdaili() {
    }

    int cur=0;
    public String[] url = new String[]{"http://www.xsdaili.com/index.php?s=/index/mfdl/type/2/p/","http://www.xsdaili.com/index.php?s=/index/mfdl/type/1/p/"};

    public static void main(String[] args) throws XpathSyntaxErrorException {
//        new IPCrawler_xsdaili().start();
        List l= new IPCrawler_xsdaili().getIP("","驻马店");
        System.out.println(l.size());
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
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>\\W{0,20}?<td>(\\d{1,5})</td>";
    }

    @Override
    public List<IP> getIP(String provinceName, String cityName) {
        String url = "http://www.xsdaili.com/mfdl?type=1&city="+cityName;
        return crawl(url);
    }
}
