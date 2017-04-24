package code.jtduan.controller;

import code.jtduan.crawler.Cities;
import code.jtduan.crawler.IPCheck;
import code.jtduan.crawler.proxypool.IP;
import code.jtduan.crawler.proxypool.IPCrawler_66ip;
import code.jtduan.crawler.proxypool.IPCrawler_Data5u;
import code.jtduan.crawler.proxypool.IPCrawler_Zdaye;
import code.jtduan.crawler.proxypool.IPCrawler_qiaodm;
import code.jtduan.crawler.proxypool.IPCrawler_superfast;
import code.jtduan.crawler.proxypool.IPCrawler_xsdaili;
import code.jtduan.service.IPService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jintaoduan on 17/4/24.
 */
@Controller
public class IPController {

    @Autowired
    private IPService ipService;

    @RequestMapping("getIpByCity")
    @ResponseBody
    public String getIp(String city){
        List<IP> ips = new ArrayList<>();
        ips.addAll(new IPCrawler_Zdaye().getIP("",city));
        ips.addAll(new IPCrawler_superfast().getIP("",city));
        ips.addAll(new IPCrawler_Data5u().getIP("",city));
        ips.addAll(new IPCrawler_66ip().getIP("",city));
        ips.addAll(new IPCrawler_qiaodm().getIP("",city));
        ips.addAll(new IPCrawler_xsdaili().getIP("",city));
        for(IP ip: ips){
            IPCheck.check(ip,"AutoHand");
        }
        return ips.size()+"";
    }

    @RequestMapping("getnoIpCity2")
    @ResponseBody
    public String getnoIp2(){
        StringBuffer sb = new StringBuffer();
        for(String str:Cities.str2.split("、")){
            List list = ipService.selectIpByCity(str);
            sb.append(str+"："+list.size()).append("<br />");
        }
        return sb.toString();
    }

    @RequestMapping("getnoIpCity3")
    @ResponseBody
    public String getnoIp3(){
        StringBuffer sb = new StringBuffer();
        for(String str:Cities.str3.split("、")){
            List list = ipService.selectIpByCity(str);
            sb.append(str+"："+list.size()).append("<br />");
        }
        return sb.toString();
    }

    @RequestMapping("getnoIpCity4")
    @ResponseBody
    public String getnoIp4(){
        StringBuffer sb = new StringBuffer();
        for(String str:Cities.str4.split("、")){
            List list = ipService.selectIpByCity(str);
            sb.append(str+"："+list.size()).append("<br />");
        }
        return sb.toString();
    }

    @RequestMapping("getnoIpCity5")
    @ResponseBody
    public String getnoIp5(){
        StringBuffer sb = new StringBuffer();
        for(String str:Cities.str5.split("、")){
            List list = ipService.selectIpByCity(str);
            sb.append(str+"："+list.size()).append("<br />");
        }
        return sb.toString();
    }
}
