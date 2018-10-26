package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Named;
import javax.sql.DataSource;

/**
 * Created by sukang on 2018/8/3.
 */

@Configuration
public class DataSourceConfig {


    @ConfigurationProperties(prefix = "spring.datasource.first")
    @Bean(name = "firstSource")
    public DataSource firstSource(){
       return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.second")
    @Primary
    @Bean(value = "secondSource")
    public DataSource secondSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate firstJdbcTemplate(
            @Named(value = "firstSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);

    }

    @Bean(name = "secondJdbcTemplate")
    @Primary
    public JdbcTemplate secondJdbcTemplate(
            @Named(value = "secondSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }




}
