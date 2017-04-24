package code.jtduan.crawler;

import org.apache.commons.lang3.StringUtils;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import code.jtduan.util.OKHttpUtil;
import code.jtduan.util.XDocument;

/**
 * Created by jintaoduan on 17/4/20.
 */
public class BaiduCrawler {

    public String getHtml(){
        String html= OKHttpUtil.sendGet("http://www.baidu.com/s?ie=UTF-8&wd=ip","");
        return html;
    }

    public static void main(String[] args) throws XpathSyntaxErrorException {
        String html = new BaiduCrawler().getHtml();
        if(StringUtils.isEmpty(html)) {
            System.out.println("BLANK");
            return;
        }
        XDocument doc = new XDocument(html);
        String location = doc.selFirst("/body//div[@class*='op-ip-detail']/allText()");
        System.out.println(location);
    }
}
