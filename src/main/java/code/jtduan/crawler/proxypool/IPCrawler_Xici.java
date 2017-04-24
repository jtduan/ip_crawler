package code.jtduan.crawler.proxypool;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 不能区分城市进行查询
 * 大概率不可用
 */
@Component
public class IPCrawler_Xici extends IPRegexCrawler{
    public IPCrawler_Xici() {
    }

    int cur=0;
    String[] url = new String[]{"http://www.xicidaili.com/nn/","http://www.xicidaili.com/nt/","http://www.xicidaili.com/wn/","http://www.xicidaili.com/wt/"};

    @Override
    public String getNextUrl() {
        if(cur>=8) return "";
        String next = url[cur%4]+(cur/4+1);
        cur++;
        return next;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{1,10}?<td>(\\d{1,5})</td>";
    }

    public static void main(String[] args) {
        new IPCrawler_Xici().start();
    }
}
