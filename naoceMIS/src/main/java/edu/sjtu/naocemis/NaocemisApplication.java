package edu.sjtu.naocemis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories // 开启jpa
@EnableTransactionManagement // 开启事务注解
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NaocemisApplication {
	private static final Logger log = LoggerFactory.getLogger(NaocemisApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(NaocemisApplication.class, args);
		log.info("启动成功");
	}

}
