package com.luckv.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LuckVApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckVApplication.class, args);
	}

}
