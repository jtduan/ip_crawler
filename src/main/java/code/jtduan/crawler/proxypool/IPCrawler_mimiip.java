package code.jtduan.crawler.proxypool;

import java.util.Collections;
import java.util.List;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 可用率相对高
 */
@Component
public class IPCrawler_mimiip extends IPRegexCrawler implements CityIPCrawler{
    public IPCrawler_mimiip() {
    }

    int cur=-1;
    public String[] url = new String[]{"http://www.mimiip.com/gnpu/","http://www.mimiip.com/gngao/","http://www.mimiip.com/gntou/"};

    public static void main(String[] args) throws XpathSyntaxErrorException {
        new IPCrawler_mimiip().start();
    }

    @Override
    public List<IP> getIP(String provinceName, String cityName) {
        return Collections.emptyList();
    }

    @Override
    public String getNextUrl() {
        cur++;
        if(cur>=9) return "";
        return url[cur%3]+(cur/3+1);
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{1,10}?<td>(\\d{1,5})</td>";
    }
}
