package code.jtduan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by summer on 2017/4/21.
 */

public class JsUtils {
    public static String getAjaxCotnent(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        //phantomjs 和codes.js的路径之间有个空格 本代码只是测试用的绝对路径
        Process p = rt.exec("phantomjs G:\\jtduanFiles\\phantomjs-2.1.1-windows\\bin\\start.js "+url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while((tmp = br.readLine())!=null){
            sbf.append(tmp);
        }
        //System.out.println(sbf.toString());
        return sbf.toString();
    }

    public static void main(String[] args) throws IOException {
        String content = getAjaxCotnent("http://cq.qq.com/baoliao/detail.htm?294064");
        assert  content != null;
    }
}