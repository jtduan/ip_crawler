package code.jtduan.crawler.proxypool;

import java.util.List;

/**
 * Created by jintaoduan on 17/4/22.
 */
public interface CityIPCrawler {
    public List<IP> getIP(String provinceName, String cityName);
}
