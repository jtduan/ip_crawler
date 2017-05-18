package code.jtduan.crawler.proxypool.html;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

/**
 * Created by summer on 2017/4/22.
 */
public class SeleniumGeter implements HtmlGeter{
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        String PROXY = "127.0.0.1:1080";
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(PROXY)
                .setFtpProxy(PROXY)
                .setSocksProxy(PROXY)
                .setSslProxy(PROXY);
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        capabilities.setCapability(CapabilityType.PROXY, proxy);

//        capabilities.setJavascriptEnabled(true);
        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:8910"), DesiredCapabilities.phantomjs());
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.baidu.com/#ie=UTF-8&wd=ip");
        Thread.sleep(2000);
        String str = driver.getPageSource();
        driver.close();
        System.out.println(str);
    }

    @Override
    public String getHtml(String url) {
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
            String PROXY = "127.0.0.1:1080";
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(PROXY)
                    .setFtpProxy(PROXY)
                    .setSocksProxy(PROXY)
                    .setSslProxy(PROXY);
            proxy.setProxyType(Proxy.ProxyType.MANUAL);
            capabilities.setCapability(CapabilityType.PROXY, proxy);

//            WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.phantomjs());
//            WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:8910"), DesiredCapabilities.phantomjs());
            WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:8910"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);
            Thread.sleep(2000);
            String str = driver.getPageSource();
            driver.close();
            return str;
        } catch (Exception e) {
            return "";
        }
    }

}
