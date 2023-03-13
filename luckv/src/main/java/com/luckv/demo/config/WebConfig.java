package com.luckv.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
		public void addCorsMappings(CorsRegistry registry) {
	        // WebMvcConfigurer.super.addCorsMappings(registry);
	        // registry.addMapping("/**").allowedOrigins("http://localhost:8090");    // localhost:8090에서만 접속 가능
	        registry.addMapping("/**").allowedOrigins("*");                            // 어디에서든 접속 가능
	    }
}
