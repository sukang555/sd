package com.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sukang
 */
@Controller
public class LoginController {


    @GetMapping("/userLogin")
    public String userLogin(){
        return "login";

    }

    @GetMapping("/index")
    public String userIndex(){
        return "index";
    }

}
