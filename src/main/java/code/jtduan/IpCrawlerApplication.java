package code.jtduan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IpCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpCrawlerApplication.class, args);
	}
}
