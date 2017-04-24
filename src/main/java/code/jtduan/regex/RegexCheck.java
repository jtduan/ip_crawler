package code.jtduan.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jintaoduan on 17/4/13.
 */
public class RegexCheck {
    /**
     * 检验用户名
     * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾
     * 有最小位数限制的用户名，比如：用户名最少为4位字符
     * */
    public boolean checkUsername(String username,int min) {
        //[\\w\u4e00-\u9fa5]{2,}?
        String regex="[\\w\u4e00-\u9fa5]{"+min+",}(?<!_)";

        return startCheck(regex,username);
    }


    /**
     * 验证国内身份证号码：15或18位，由数字组成，不能以0开头
     * */
    public boolean checkIdCard(String idNr) {
        String reg="^[1-9](\\d{14}|\\d{17})";

        return startCheck(reg,idNr);
    }


    public  boolean startCheck(String reg,String string) {
        boolean tem=false;

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher=pattern.matcher(string);

        tem=matcher.matches();
        return tem;
    }
}
