package com.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.family.*")
@EnableAutoConfiguration
public class FamilyWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyWsApplication.class, args);
	}

}
