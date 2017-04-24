package code.jtduan;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.htmlcleaner.XPatherException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;

/**
 * Created by jintaoduan on 17/4/19.
 */
public class JsoupXpath {

    public static void main(String[] args) throws IOException, XPatherException, ParserConfigurationException, XPathExpressionException, XpathSyntaxErrorException {
        String url = "http://zhidao.baidu.com/daily";
//        String exp = "//a[@href*='5']/@href | /a[@href*='3']/@href";
//        String exp = "//img[@src*='3948'][@width*='800']/@src";
        String exp = "//div[self::div[@class='daily-cont']//h2/a/text()*='胖子']/div[@class='daily-info']/allText()";
        String html = null;
        try {
            Connection connect = Jsoup.connect(url);
            html = connect.get().body().html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JXDocument doc = new JXDocument(html);
        List<Object> list = doc.sel(exp);
        for(Object obj : list){
            System.out.println(obj);
        }
    }
}
