package com.controller;


import com.common.dto.UserInfoDTO;
import com.common.entity.SysMenu;
import com.controller.core.BaseController;
import com.controller.core.SecurityContextUtils;
import com.service.MenuService;
import com.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return "/login";
    }

    @GetMapping("/")
    public String indexHtml(){
        return "redirect:/index";
    }


    @GetMapping("/index/system/{page}")
    public String homeHtml(@PathVariable("page") String page){
        if (StringUtils.isBlank(page)){
            return "/index";
        }
        StringBuilder pathString = new StringBuilder();
        Arrays.stream(page.split("-")).forEach(t -> pathString.append("/").append(t));
        return "/system" + pathString.toString() ;
    }







    @GetMapping("/index")
    public String userIndex(Model model){

        List<SysMenu> sysMenus = menuServiceImpl.getListBySortOk();
        Map<String, SysMenu> sysMenuMap = sysMenus.stream()
                .collect(Collectors.toMap( t -> String.valueOf(t.getId()),Function.identity()));

        // 封装菜单树形数据
        Map<String, SysMenu> treeMenu = buildTreeMenu(sysMenuMap);

        //构建用户信息
        UserInfoDTO userInfoDto = buildUserInfo();

        model.addAttribute("treeMenu", treeMenu);
        model.addAttribute("userInfo", userInfoDto);
        return "/index";
    }

    private UserInfoDTO buildUserInfo() {
        User currentUser = SecurityContextUtils.getCurrentUser();
        return userInfoService.selectByUserName(currentUser.getUsername());
    }


    private Map<String, SysMenu> buildTreeMenu(Map<String, SysMenu> sysMenuMap){

        Map<String, SysMenu> treeMenu = new LinkedHashMap<>(16);


        List<SysMenu> collect = sysMenuMap.values().stream()
                .filter(t -> 0L == t.getPid())
                .sorted(Comparator.comparing(SysMenu::getSort))
                .collect(Collectors.toList());

        collect.forEach(t -> treeMenu.put(String.valueOf(t.getId()),t));

        treeMenu.forEach((k,v) -> {
            addChildrenNode(sysMenuMap,treeMenu,v);
        });

        return treeMenu;
    }

    private void addChildrenNode(Map<String, SysMenu> sysMenuMap,Map<String, SysMenu> treeMenuMap, SysMenu sysMenu) {

        if (StringUtils.isBlank(sysMenu.getPids())){
            return;
        }

        String[] childIds = sysMenu.getPids().split(":");

        for (String childId : childIds) {
            SysMenu childMenu = sysMenuMap.get(childId);
            if (childMenu == null){
                continue;
            }
            sysMenu.getChildren().put(childId,childMenu);
            addChildrenNode(sysMenuMap,treeMenuMap,childMenu);
        }

    }














}
