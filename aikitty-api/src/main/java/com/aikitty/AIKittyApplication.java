package com.aikitty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 * 启动类
 * @author haungliyang
 * 
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AIKittyApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(com.aikitty.AIKittyApplication.class,args);
		
	}

	
	
}
