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
			registry.addMapping("/**")
		    .allowedOrigins("*") /* 모든 클라이언트 요청 허용 */
		    .allowedMethods("*") /* 클라이언트에서 보내는 모든 요청 메소드 허용 */
		    .exposedHeaders("VICTOKEN"); /* 클라이언트에서 Header에 접근 허용 */
	}
}
