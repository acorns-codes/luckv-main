package com.luckv.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisConfig {
	
	@Autowired
    @Qualifier(value="luckvDataSource")
    private DataSource luckvDataSource;
	
	@Bean
    @Primary
    public SqlSessionFactory luckvSqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(luckvDataSource);

        /** mapper xml 파일 경로 설정 */
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);

        /** alias 설정 com.package..entity,Board -> resultType"Board" */
        sqlSessionFactoryBean.setTypeAliasesPackage("com.luckv.demo.vo");

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        /** 실제 DB 컬럼명 스네이크 표기법 = 카멜케이스 표기법 맵핑 */
        configuration.setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }
    @Bean
    @Primary
    public SqlSession luckvSqlSession() throws Exception{
        return new SqlSessionTemplate(luckvSqlSessionFactoryBean());
    }
}
