package com.luckv.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false) // Swagger 에서 제공해주는 기본 응답 코드를 표시할 것이면 true
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.luck.demo.controller")) // Controller가 들어있는 패키지. 이 경로의 하위에 있는 api만 표시됨.
                .paths(PathSelectors.any()) // 위 패키지 안의 api 중 지정된 path만 보여줌. (any()로 설정 시 모든 api가 보여짐)
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("luckV SpringBoot Rest API")
                .description("동영상경매사이트의 swagger api 입니다")
                .version("0.1")
                .build();
    }
}
