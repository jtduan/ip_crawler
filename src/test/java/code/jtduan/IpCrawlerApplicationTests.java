package code.jtduan;

import code.jtduan.crawler.IPCheck;
import code.jtduan.service.IPRepo;
import code.jtduan.service.IPService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpCrawlerApplicationTests {

	@Autowired
	IPRepo ipRepo;

	@Test
	public void contextLoads() {
//		IPService.add("60.178.0.74",8081,"1","ddd");
		System.out.println(ipRepo.findByIp("60.178.0.74:8081").getCity());
	}

}
