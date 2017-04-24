package code.jtduan.util;//package code.jtduan.util;
//
//import java.net.URL;
//
///**
// * Created by summer on 2017/4/21.
// */
//public class PhantomJs {
//    public static void main(String[] args) {
//        profile = new FirefoxProfile();
//        profile.setPreference("general.useragent.override",testData.getUserAgent());
//        capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability("firefox_profile", profile);
//        driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"),capabilities);
//        driverWait = new WebDriverWait(driver,TestConstant.WAIT_ELEMENT_TO_LOAD);
//        driver.get("http://www.google.com");
//
//        WebDriver driver = new RemoteWebDriver(
//                new URL("http://127.0.0.1:8910"),
//                DesiredCapabilities.phantomjs());
//    }
//}
