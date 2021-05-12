
package com.app.base.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {
    
    // DB 접속정보는 application.properties에 있다.
	/*
    @Bean
    public DataSource customDataSource() {
        return DataSourceBuilder.create()
                                .url("jdbc:mysql://ip:3306/~?useSSL=false&serverTimezone=UTC")
                                .driverClassName("com.mysql.cj.jdbc.Driver")
                                .username("hosuk")
                                .password("hosuk")
                                .build();
    }
    */
    
    // 추가 설정이 필요한 config.xml 설정
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            
            sessionFactory.setConfigLocation(
            		         new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/config/sql-mapper-config.xml"));
            
            Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/app/**/*.xml");
            
            sessionFactory.setMapperLocations(res);
            
            return sessionFactory.getObject();
    }
 
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}