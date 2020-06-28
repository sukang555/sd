package com.config.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**filterMap.put("/css/**",ANON);
 filterMap.put("/images",ANON);
 filterMap.put("/js/**",ANON);
 filterMap.put("/layui/**",ANON);
 filterMap.put("/*.ico",ANON);
 * @Author: sukang
 * @Date: 2020/6/28 15:26
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final LinkedList<String> ANON_PATH = new LinkedList<>();

    static {
        ANON_PATH.add("/css/**");
        ANON_PATH.add("/images/**");
        ANON_PATH.add("/js/**");
        ANON_PATH.add("/layui/**");
        ANON_PATH.add("/favicon.ico");
        ANON_PATH.add("/");
        ANON_PATH.add("/public/**");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(ANON_PATH.toArray(new String[]{}));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
