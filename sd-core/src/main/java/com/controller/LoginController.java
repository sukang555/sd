package com.controller;


import com.common.dto.UserInfoDTO;
import com.common.entity.SysMenu;
import com.common.entity.UserDetail;
import com.common.entity.UserInfo;
import com.common.enums.MenuTypeEnum;
import com.common.util.BeanUtil;
import com.controller.core.BaseController;
import com.service.MenuService;
import com.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author sukang
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private MenuService menuServiceImpl;


    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/userLogin")
    public String userLogin(){
        return "login";
    }

    @GetMapping("/")
    public String indexHtml(){
        return "redirect:/index";
    }


    @GetMapping("/index/system/{page}")
    public String homeHtml(@PathVariable("page") String page){
        if (StringUtils.isBlank(page)){
            return "index";
        }
        StringBuilder pathString = new StringBuilder();
        Arrays.stream(page.split("-")).forEach(t -> pathString.append("/").append(t));
        return "/system/" + pathString.toString() ;
    }







    @GetMapping("/index")
    public String userIndex(Model model){

        List<SysMenu> sysMenus = menuServiceImpl.getListBySortOk();
        Map<String, SysMenu> sysMenuMap = sysMenus.stream().collect(Collectors.toMap(String::valueOf, Function.identity()));

        // 封装菜单树形数据
        Map<String, SysMenu> treeMenu = buildTreeMenu(sysMenuMap);

        //构建用户信息
        UserInfoDTO userInfoDto = buildUserInfo();

        model.addAttribute("treeMenu", treeMenu);
        model.addAttribute("userInfo", userInfoDto);
        return "index";
    }

    private UserInfoDTO buildUserInfo() {
        User details = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userInfoService.selectByUserName(details.getUsername());
    }


    private Map<String, SysMenu> buildTreeMenu(Map<String, SysMenu> sysMenuMap){

        Map<String, SysMenu> treeMenu = new HashMap<>(16);

        sysMenuMap.forEach((k,v) -> {
            if (StringUtils.isEmpty(v.getPids())){
                treeMenu.put(k,v);
            }
        });
        return treeMenu;
    }











}
