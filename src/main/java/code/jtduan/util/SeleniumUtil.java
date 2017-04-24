package code.jtduan.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by summer on 2017/4/22.
 */
public class SeleniumUtil {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {


        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setSocksProxy("127.0.0.1:1080");
        capabilities.setCapability(CapabilityType.PROXY, proxy);

//        capabilities.setJavascriptEnabled(true);
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.baidu.com/s?ie=UTF-8&wd=ip");
        Thread.sleep(2000);
        String str = driver.getPageSource();
        driver.close();
        System.out.println(str);
    }
}
