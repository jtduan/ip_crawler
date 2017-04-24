package code.jtduan.crawler.proxypool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

/**
 * Created by jintaoduan on 17/4/21.
 */
//**
// http://www.goubanjia.com/free/index2.shtml  ==== http://ip.qiaodm.com/free/area/%E6%B3%89%E5%B7%9E%E5%B8%82-1.html
    //http://www.nianshao.me/?page=1
    //http://proxy.mimvp.com/free.php
    //http://ip.baizhongsou.com/default.aspx
    //http://www.dlip.cn/gng/index.html
    //http://www.iphai.com/
    //http://www.mayidaili.com/free/location/%E6%B5%99%E6%B1%9F%E7%9C%81-3-1784764/3
    //http://www.swei360.com/
public class Pools {

    public String URL = "http://ip.zdaye.com";
    public String Ip_Xpath ="//table[@id='ipc']//td[text()~='\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}']";

    public static void main(String[] args) throws XpathSyntaxErrorException {

        String str = "<div><tr class=\"odd\"> \n" +
                " <td class=\"country\"><img src=\"http://fs.xicidaili.com/images/flag/cn.png\" alt=\"Cn\"></td> \n" +
                " <td>60.178.84.225</td> \n" +
                " <td>808</td> \n" +
                " <td> <a href=\"/2017-04-21/zhejiang\">浙江宁波</a> </td> \n" +
                " <td class=\"country\">高匿</td> \n" +
                " <td>HTTPS</td> \n" +
                " <td class=\"country\"> \n" +
                "  <div title=\"0.395秒\" class=\"bar\"> \n" +
                "   <div class=\"bar_inner fast\" style=\"width:87%\"> \n" +
                "   </div> \n" +
                "  </div> </td> \n" +
                " <td class=\"country\"> \n" +
                "  <div title=\"0.079秒\" class=\"bar\"> \n" +
                "   <div class=\"bar_inner fast\" style=\"width:97%\"> \n" +
                "   </div> \n" +
                "  </div> </td> \n" +
                " <td>2小时</td> \n" +
                " <td>17-04-21 14:46</td> \n" +
                "</tr></div>";

//        XDocument doc = new XDocument(str);
//        Document doc = Jsoup.parse(str);
//        XDocument xdoc = new XDocument(doc);
//        System.out.println(xdoc.selFirst("/body/tr[@class='odd']/td[2]/text())"));
    }
}
