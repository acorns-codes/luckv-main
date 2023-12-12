package com.luckv.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.demo")
    public DataSource luckvDataSource() {return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
