package com.datasource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author sukang on 2018/12/29.
 */
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class MybatisConfig {


    @Bean("mySqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory (
            @Named("secondSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean("mySqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(
            @Named("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory){

        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
