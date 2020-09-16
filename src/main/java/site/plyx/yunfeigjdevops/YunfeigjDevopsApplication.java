package site.plyx.yunfeigjdevops;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("site.plyx.yunfeigjdevops.mapper")
public class YunfeigjDevopsApplication {
	public static void main(String[] args) {
		SpringApplication.run(YunfeigjDevopsApplication.class, args);
	}
}
