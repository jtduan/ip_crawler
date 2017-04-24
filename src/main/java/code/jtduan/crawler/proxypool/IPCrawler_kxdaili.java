package code.jtduan.crawler.proxypool;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 极低概率可用
 */
@Component
public class IPCrawler_kxdaili extends IPRegexCrawler{
    public IPCrawler_kxdaili() {
    }

    int cur=0;
    public String[] url = new String[]{"http://www.kxdaili.com/dailiip/1/","http://www.kxdaili.com/dailiip/2/"};

    public static void main(String[] args) throws XpathSyntaxErrorException {
        new IPCrawler_kxdaili().start();
    }

    @Override
    public String getNextUrl() {
        if(cur>=10) return "";
        String next = url[cur%2]+(cur/2+1)+".html";
        cur++;
        return next;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{1,10}?<td>(\\d{1,5})</td>";
    }
}
