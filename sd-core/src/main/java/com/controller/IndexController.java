package com.controller;


import com.common.entity.ScheduleJobEntity;
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
        return "login";
    }



    @RequestMapping("/")
    public String index(Map<String,String> map){
        map.put("name","sukang");
        return "index";

    }

}
