package code.jtduan.crawler.proxypool.html;

import code.jtduan.util.OKHttpUtil;

import org.springframework.stereotype.Component;

/**
 * Created by summer on 2017/4/22.
 */
public class SimpleHtmlGeter implements HtmlGeter{
    @Override
    public String getHtml(String url) {
        String html= OKHttpUtil.sendGet(url,"");
        html = html.replace("&nbsp;"," ").replaceAll(" +"," ");
        return html;
    }

}
