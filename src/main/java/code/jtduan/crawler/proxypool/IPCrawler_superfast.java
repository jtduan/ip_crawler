package code.jtduan.crawler.proxypool;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

/**
 * Created by jintaoduan on 17/4/21.
 * 一个api可以获取全部代理ip
 * 质量一般
 * 可以本地统计城市或直接获取城市
 */
@Component
public class IPCrawler_superfast extends IPRegexCrawler implements CityIPCrawler{
    public IPCrawler_superfast() {
    }

    int cur=0;
    public String url = "http://superfastip.com/welcome/testRedis";
    String regex="\"(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\",\"(\\d{1,5})\"";

    public static void main(String[] args) throws XpathSyntaxErrorException {
        List l = new IPCrawler_superfast().getIP("","邵阳");
        System.out.println(l.size());
    }
    @Override
    public String getNextUrl() {
        if(cur==0){
            cur++;
            return url;
        }
        return "";
    }

    @Override
    protected String getIPRegex() {
        return regex;
    }

    @Override
    public List<IP> getIP(String provinceName, String cityName) {
        this.regex = "\"(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\",\"(\\d{1,5})\",\".{0,5}"+cityName+".{0,3}\"";
        List<IP> list = crawl(url);
        this.regex="\"(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\",\"(\\d{1,5})\"";
        return list;
    }
}
