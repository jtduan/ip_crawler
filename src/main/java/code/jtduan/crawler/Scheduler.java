package code.jtduan.crawler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import code.jtduan.crawler.proxypool.IPCrawler_66ip;
import code.jtduan.crawler.proxypool.IPCrawler_Data5u;
import code.jtduan.crawler.proxypool.IPCrawler_Kuai;
import code.jtduan.crawler.proxypool.IPCrawler_Xici;
import code.jtduan.crawler.proxypool.IPCrawler_Zdaye;
import code.jtduan.crawler.proxypool.IPCrawler_baizhongsu;
import code.jtduan.crawler.proxypool.IPCrawler_dlip;
import code.jtduan.crawler.proxypool.IPCrawler_ip181;
import code.jtduan.crawler.proxypool.IPCrawler_ip3366;
import code.jtduan.crawler.proxypool.IPCrawler_iphai;
import code.jtduan.crawler.proxypool.IPCrawler_kxdaili;
import code.jtduan.crawler.proxypool.IPCrawler_mayi;
import code.jtduan.crawler.proxypool.IPCrawler_mimiip;
import code.jtduan.crawler.proxypool.IPCrawler_mimivp;
import code.jtduan.crawler.proxypool.IPCrawler_qiaodm;
import code.jtduan.crawler.proxypool.IPCrawler_superfast;
import code.jtduan.crawler.proxypool.IPCrawler_xdaili;
import code.jtduan.crawler.proxypool.IPCrawler_xsdaili;
import code.jtduan.crawler.proxypool.IPCrawler_yaoyao;

/**
 * Created by jintaoduan on 17/4/24.
 */
@Component
public class Scheduler {

    /**
     * 每次循环检测1000个（可多次）
     */
    @Scheduled(fixedDelay = 1000*60* 60)
    public void schedule_66ip(){
        new IPCrawler_66ip().start();
    }


    /**
     * 50到100个
     */
    @Scheduled(fixedDelay = 1000*60* 30)
    public void schedule_baizhongsu(){
        new IPCrawler_baizhongsu().start();
    }


    /**
     * 每次30个
     */
    @Scheduled(fixedDelay = 1000*60* 10)
    public void schedule_data5u(){
        new IPCrawler_Data5u().start();
    }

    /**
     * 每次125个（翻页）
     */
    @Scheduled(fixedDelay = 1000*60* 30)
    public void schedule_dlip(){
        new IPCrawler_dlip().start();
    }

    /**
     * 公司内部无法访问
     */
    @Scheduled(fixedDelay = 1000*60* 30)
    public void schedule_goubanjia(){
//        new IPCrawler_goubanjia().start();
    }

    /**
     * 每次200个 （翻页）
     */
    @Scheduled(fixedDelay = 1000*60* 15)
    public void schedule_ip181(){
        new IPCrawler_ip181().start();
    }

    /**
     * 每次60个 （翻页）
     */
    @Scheduled(fixedDelay = 1000*60* 20)
    public void schedule_ip3366(){
        new IPCrawler_ip3366().start();
    }

    /**
     * 每次150个
     */
    @Scheduled(fixedDelay = 1000*60* 26)
    public void schedule_iphai(){
        new IPCrawler_iphai().start();
    }

    /**
     * 每次75个（翻页）
     */
    @Scheduled(fixedDelay = 1000*60* 28)
    public void schedule_kuai(){
        new IPCrawler_Kuai().start();
    }

    /**
     * 每次100个（翻页）
     */
    @Scheduled(fixedDelay = 1000*60* 23)
    public void schedule_kxdaili(){
        new IPCrawler_kxdaili().start();
    }

    /**
     * 每次75个（翻页）
     */
    @Scheduled(fixedDelay = 1000*60* 20)
    public void schedule_mayi(){
        new IPCrawler_mayi().start();
    }

    /**
     * 每次360个（翻页）
     */
    @Scheduled(fixedDelay = 1000*60* 60)
    public void schedule_mimiip(){
        new IPCrawler_mimiip().start();
    }

    /**
     * 每次50个
     */
    @Scheduled(fixedDelay = 1000*60* 10)
    public void schedule_mimivp(){
        new IPCrawler_mimivp().start();
    }

    /**
     * 每次15个
     */
    @Scheduled(fixedDelay = 1000*60* 2)
    public void schedule_qiaodm(){
        new IPCrawler_qiaodm().start();
    }

    /**
     * 每次2000+个
     */
    @Scheduled(fixedDelay = 1000*60)
    public void schedule_superfast(){
        new IPCrawler_superfast().start();
    }

    /**
     * 每次10个,10分钟更新
     */
    @Scheduled(fixedDelay = 1000*60* 9)
    public void schedule_xdaili(){
        new IPCrawler_xdaili().start();
    }

    /**
     * 每次800个，翻页
     */
    @Scheduled(fixedDelay = 1000*60* 50)
    public void schedule_xici(){
        new IPCrawler_Xici().start();
    }

    /**
     * 每次200个，翻页
     */
    @Scheduled(fixedDelay = 1000*60* 15)
    public void schedule_xsdaili(){
        new IPCrawler_xsdaili().start();
    }

    /**
     * 每次180个，翻页
     */
    @Scheduled(fixedDelay = 1000*60* 12)
    public void schedule_yaoyao(){
        new IPCrawler_yaoyao().start();
    }

    /**
     * 每次350个
     */
    @Scheduled(fixedDelay = 1000*60* 30)
    public void schedule_Zdaye(){
        new IPCrawler_Zdaye().start();
    }

}
