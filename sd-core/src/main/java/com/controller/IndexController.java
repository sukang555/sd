package com.controller;


import com.common.entity.ScheduleJobEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sukang
 */
@Controller
public class IndexController {

    @Resource
    private JdbcTemplate primaryJdbcTemplate;

    @RequestMapping("/success")
    public String success(Map<String,String> map){
        return "success";

    }

    @RequestMapping("/data")
    public String data(Map<String,Object> map){

        StringBuilder sql = new StringBuilder(" SELECT * FROM schedule_job ");
        BeanPropertyRowMapper<ScheduleJobEntity> rowMapper =
                new BeanPropertyRowMapper<>(ScheduleJobEntity.class);

        List<ScheduleJobEntity> query = primaryJdbcTemplate.query(sql.toString(), rowMapper);
        map.put("query",query);
        return "data";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/login-user")
    public String login(HttpServletRequest request, Map<String, String> map){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        String msg = "";
        try {
            //完成登录
            subject.login(usernamePasswordToken);
            return "success";
        } catch(UnknownAccountException e1) {
            msg = e1.getMessage();
        }catch (IncorrectCredentialsException e2){
            msg = "密码错误";
        }catch (Exception e3){
            msg = "登陆异常";
            e3.printStackTrace();
        }
        map.put("msg", msg);
        return "login";

    }



    @RequestMapping("/")
    public String index(Map<String,String> map){
        map.put("name","sukang");
        return "index";

    }

}
