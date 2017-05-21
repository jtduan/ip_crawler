package code.jtduan.crawler.proxypool;

import code.jtduan.crawler.proxypool.html.HtmlGeter;
import code.jtduan.crawler.proxypool.html.SeleniumGeter;
import code.jtduan.util.StringUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * 不可分城市
 * 代理质量较差
 */
@Component
public class IPCrawler_Zdaye extends IPRegexCrawler implements CityIPCrawler{
    public IPCrawler_Zdaye() {
        super(new SeleniumGeter());
    }

    private int curPort=-1;
    private int curCity=0;

    private String[] ports= new String[]{"80","808","8080","3128","8081","9999","8998"};

    private String[] cities= new String[]{"广东","山东","江苏","河南","河北","浙江","陕西","湖南","重庆","福建","云南","四川","广西","安徽","海南","江西","湖北","山西","辽宁","台湾","黑龙江","内蒙古","贵州","甘肃","青海","吉林","宁夏"};

    private String[] url =new String[]{"http://ip.zdaye.com/?ip=&port=${port}&adr=${city}&checktime=&sleep=&cunhuo=&nport=&nadr=&dengji=&https=&yys=&gb=&post=&px="};

    public IPCrawler_Zdaye(HtmlGeter htmlGeter) {
        super(htmlGeter);
    }

    @Override
    public String getNextUrl() {
        if(curPort>=ports.length-1){
            curPort=0;
            curCity++;
        }else{
            curPort++;
        }
        if(curCity==cities.length){
            return "";
        }
        String next = null;
        try {
            next = url[0].replace("${port}",ports[curPort]).replace("${city}", URLEncoder.encode(cities[curCity],"GBK"));
        } catch (UnsupportedEncodingException e) {
        }
        return next;
    }

    @Override
    protected String getIPRegex() {
        return "(?s)<td.*?>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</td>.{0,10}?<td.*?>(\\d{1,5})</td>";
    }

    @Override
    public List<IP> crawl(String url) {
        List<IP> list = super.crawl(url);
        for(IP ip:list){
            ip.setPort(StringUtil.getMatch(url,"port=(\\d+)\\b"));
        }
        return list;
    }

    public static void main(String[] args) {
        List l = new IPCrawler_Zdaye(new SeleniumGeter()).getIP("","信阳");
        System.out.println(l.size());
//        try {
//            System.out.println(URLEncoder.encode("广东","GBK"));
//        } catch (UnsupportedEncodingException e) {
//        }
    }

    @Override
    public List<IP> getIP(String provinceName, String cityName) {
        List<IP> ips = new ArrayList<>();
        for(int i=0;i<ports.length;i++) {
            String url = null;
            try {
                url = this.url[0].replace("${port}",ports[i]).replace("${city}", URLEncoder.encode(cityName,"GBK"));
            } catch (UnsupportedEncodingException e) {
                return Collections.emptyList();
            }
            ips.addAll(crawl(url));
        }
        return ips;
    }
}
