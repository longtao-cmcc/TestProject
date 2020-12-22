package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableCaching
//@EnableWebSecurity
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.demo.DemoApplication.class, args);
	}

}
