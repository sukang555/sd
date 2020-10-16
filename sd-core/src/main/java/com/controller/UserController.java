package com.controller;

import com.common.dto.ResponseBean;
import com.common.dto.UserInfoDTO;
import com.controller.core.SecurityContextUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.service.UserInfoService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: sukang
 * @Date: 2020/10/1 17:24
 */
@Controller
public class UserController {

    @Resource
    private UserInfoService userInfoService;


    @PostMapping(value = "/user-info/list")
    @ResponseBody
    public ResponseBean getUserInfoList(@RequestBody UserInfoDTO userInfoDTO){
        PageInfo<UserInfoDTO> userInfos = userInfoService.getUserInfos(userInfoDTO);
        return ResponseBean.ok(userInfos);
    }


    @GetMapping(value = "/user/userInfo")
    public String getUserInfo(Model model){
        User currentUser = SecurityContextUtils.getCurrentUser();
        UserInfoDTO userInfoDTO = userInfoService.getUserDetailByUserName(currentUser.getUsername());

        model.addAttribute("user",userInfoDTO);
        return "/system/main/userInfo";
    }

}
