package code.jtduan.crawler.proxypool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import code.jtduan.crawler.proxypool.html.SeleniumGeter;
import code.jtduan.util.StringUtil;
import code.jtduan.util.XDocument;

import org.springframework.stereotype.Component;

/**
 * Created by jintaoduan on 17/4/21.
 * TODO：
 */
@Component
public class IPCrawler_qiaodm extends IPXpathCrawler implements CityIPCrawler{
    public IPCrawler_qiaodm() {
        super(new SeleniumGeter());
    }

    int cur=0;
    public String url = "http://ip.qiaodm.com/free/hot/cn.html";


    public static void main(String[] args) throws XpathSyntaxErrorException {
//        new IPCrawler_qiaodm().start();
        List l = new IPCrawler_qiaodm().getIP("","驻马店");
        System.out.println(l.size());
    }

    @Override
    public List<IP> getIP(String provinceName, String cityName) {
        if(!cityName.endsWith("市")){
            cityName = cityName+"市";
        }
        String url = null;
        try {
            url = "http://ip.qiaodm.com/free/area/"+ URLEncoder.encode(cityName,"UTF-8")+"-1.html";
        } catch (UnsupportedEncodingException e) {
            url="";
        }
        return crawl(url);
    }

    protected String getIP(String str){
        str = str.replaceAll("<(\\w{1,4})[^</>]*?none[^</>]*?>.*?</\\1>", "");
        str = str.replaceAll("<.*?>", "").replaceAll("[^0-9.:]","");
        String ip = StringUtil.getMatch(str,"\\b(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\b");
        return ip;
    }

    @Override
    public String getNextUrl() {
        if(cur==0) return url;
        return "";
    }

    @Override
    protected String getPortXpath() {
        return "//table[@class='iplist']//tr/td[@class*='port']/allText()";
    }

    @Override
    protected String getIPXpath() {
        return "//table[@class='iplist']//tr/td[@class='ip']/child::node()";
    }
}
