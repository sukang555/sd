package com.controller;

import com.common.dto.UserInfoDTO;
import com.controller.core.SecurityContextUtils;
import com.service.UserInfoService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author: sukang
 * @Date: 2020/10/1 17:24
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping(value = "/userInfo")
    public String getUserInfo(Model model){
        User currentUser = SecurityContextUtils.getCurrentUser();
        UserInfoDTO userInfoDTO = userInfoService.getUserDetailByUserName(currentUser.getUsername());

        model.addAttribute("user",userInfoDTO);
        return "/system/main/userInfo";
    }

}
