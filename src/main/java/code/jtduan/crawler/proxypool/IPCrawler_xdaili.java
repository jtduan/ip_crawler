package code.jtduan.crawler.proxypool;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 可用率高，但是量少，10分钟更新一次，每次10个
 */
@Component
public class IPCrawler_xdaili extends IPRegexCrawler{
    public IPCrawler_xdaili() {
    }

    int cur=0;
    public String url = "http://www.xdaili.cn/ipagent/freeip/getFreeIps?page=1&rows=10";


    public static void main(String[] args) throws XpathSyntaxErrorException {
        new IPCrawler_xdaili().start();
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
        return "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\b.*?port.*?(\\d{1,5})";
    }
}
