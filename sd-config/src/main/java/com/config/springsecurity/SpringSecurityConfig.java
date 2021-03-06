package com.config.springsecurity;



import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


import javax.annotation.Resource;

/**
 * @Author: sukang
 * @Date: 2020/6/28 15:26
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private AuthenticationFailureHandler sdAuthenticationFailureHandler;

    @Resource
    private SdAuthenticationSuccessHandler sdAuthenticationSuccessHandler;

    @Resource
    private UserDetailsService userDetailsService;




    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**","/actuator/**");
    }


    /**
     * openidLogin()	用于基于 OpenId 的验证
     * headers()	将安全标头添加到响应
     * cors()	配置跨域资源共享（ CORS ）
     * sessionManagement()	允许配置会话管理
     * portMapper()	允许配置一个PortMapper(HttpSecurity#(getSharedObject(class)))，其他提供SecurityConfigurer的对象使用 PortMapper 从 HTTP 重定向到 HTTPS 或者从 HTTPS 重定向到 HTTP。默认情况下，Spring Security使用一个PortMapperImpl映射 HTTP 端口8080到 HTTPS 端口8443，HTTP 端口80到 HTTPS 端口443
     * jee()	配置基于容器的预认证。 在这种情况下，认证由Servlet容器管理
     * x509()	配置基于x509的认证
     * rememberMe	允许配置“记住我”的验证
     * authorizeRequests()	允许基于使用HttpServletRequest限制访问
     * requestCache()	允许配置请求缓存
     * exceptionHandling()	允许配置错误处理
     * securityContext()	在HttpServletRequests之间的SecurityContextHolder上设置SecurityContext的管理。 当使用WebSecurityConfigurerAdapter时，这将自动应用
     * servletApi()	将HttpServletRequest方法与在其上找到的值集成到SecurityContext中。 当使用WebSecurityConfigurerAdapter时，这将自动应用
     * csrf()	添加 CSRF 支持，使用WebSecurityConfigurerAdapter时，默认启用
     * logout()	添加退出登录支持。当使用WebSecurityConfigurerAdapter时，这将自动应用。默认情况是，访问URL”/ logout”，使HTTP Session无效来清除用户，清除已配置的任何#rememberMe()身份验证，清除SecurityContextHolder，然后重定向到”/login?success”
     * anonymous()	允许配置匿名用户的表示方法。 当与WebSecurityConfigurerAdapter结合使用时，这将自动应用。 默认情况下，匿名用户将使用org.springframework.security.authentication.AnonymousAuthenticationToken表示，并包含角色 “ROLE_ANONYMOUS”
     * formLogin()	指定支持基于表单的身份验证。如果未指定FormLoginConfigurer#loginPage(String)，则将生成默认登录页面
     * oauth2Login()	根据外部OAuth 2.0或OpenID Connect 1.0提供程序配置身份验证
     * requiresChannel()	配置通道安全。为了使该配置有用，必须提供至少一个到所需信道的映射
     * httpBasic()	配置 Http Basic 验证
     * addFilterAt()	在指定的Filter类的位置添加过滤器
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置url的访问权限
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/lib/**").permitAll()
                .antMatchers("/*.ico").permitAll()
        //登录 登出页面
                .antMatchers("/login.html","/404.html","/authentication/form").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页面路径
                .loginPage("/userLogin").permitAll()
                //指定登录数据请求
                .loginProcessingUrl("/authentication/form").permitAll()
                //先指定handler 再指定重定向url
                .failureHandler(sdAuthenticationFailureHandler)
                .failureUrl("/userLogin?error=true").permitAll()
                .successHandler(sdAuthenticationSuccessHandler)
                ;
        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
        http .csrf().disable();


        http.headers().frameOptions().sameOrigin();


    }

    /**
     *  意思是，AuthenticationManagerBuilder 用于创建一个 AuthenticationManager，
     *  让我能够轻松的实现内存验证、LADP验证、
     *  基于JDBC的验证、添加UserDetailsService、添加AuthenticationProvider。
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 加密 密码验证 bean
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
