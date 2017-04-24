package code.jtduan.crawler.proxypool;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 数量少，port隐藏
 */
@Component
public class IPCrawler_mimivp extends IPRegexCrawler{
    public IPCrawler_mimivp() {
    }

    int cur=-1;
    String[] url = new String[]{"http://proxy.mimvp.com/index.php?pageindex=cn","http://proxy.mimvp.com/free.php?proxy=in_tp","http://proxy.mimvp.com/free.php?proxy=in_hp"};

    @Override
    public String getNextUrl() {
        if(cur>=2)return "";
        cur++;
        return url[cur];
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>(.)";
    }

    @Override
    public List<IP> crawl(String url) {
        List<IP> list = super.crawl(url);
        for(IP ip:list){
            ip.setPort("-1");
        }
        return list;
    }

    public static void main(String[] args) {
        new IPCrawler_mimivp().start();
    }
}
