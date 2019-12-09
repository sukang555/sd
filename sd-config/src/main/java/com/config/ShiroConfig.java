package com.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sukang
 */
@Configuration
public class ShiroConfig {

    private final static String ANON = "anon";

    private final static String AUTHC = "authc";

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //过滤器
        Map<String,String> filterMap = new LinkedHashMap<>();
        //顺序判断
        filterMap.put("/static/**",ANON);
        filterMap.put("/js/**",ANON);



        filterMap.put("/public/**",ANON);
        filterMap.put("/logout", "logout");
        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterMap.put("/", ANON);
        filterMap.put("/login-user",ANON);
        filterMap.put("/**",AUTHC);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //成功页面
        shiroFilterFactoryBean.setSuccessUrl("/success");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }



    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        return new MyShiroRealm();
    }


}
