package com.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Named;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author   sukang on 2018/8/3.
 */

@Configuration
public class DataSourceConfig {


    @ConfigurationProperties(prefix = "spring.datasource.first")
    @Bean(name = "firstSource")
    public DataSource firstSource(){
       return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.second")
    @Bean(value = "secondSource")
    public DataSource secondSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean(name = "primaryJdbcTemplate")
    @Primary
    public JdbcTemplate firstJdbcTemplate(
            @Named(value = "firstSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);

    }

    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(
            @Named(value = "secondSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    @Bean
    public HandlerDataSource dataSource(
            @Named("firstSource") DataSource firstDataSource,
            @Named("secondSource") DataSource secondDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.FIRST, firstDataSource);
        targetDataSources.put(DataSourceNames.SECOND, secondDataSource);
        return new HandlerDataSource(firstDataSource, targetDataSources);
    }




}
