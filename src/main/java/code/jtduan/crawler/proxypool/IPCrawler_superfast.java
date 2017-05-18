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
public class IPCrawler_superfast extends IPRegexCrawler {
    public IPCrawler_superfast() {
    }

    int cur = 0;
    public String url = "http://superfastip.com/welcome/getips/";
    String regex = "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{1,10}?<td>(\\d{1,5})</td>";

    public static void main(String[] args) {
        new IPCrawler_superfast().start();
    }

    @Override
    public String getNextUrl() {
        if (cur <= 2) {
            cur++;
            return url + cur;
        }
        return "";
    }

    @Override
    protected String getIPRegex() {
        return regex;
    }
}
