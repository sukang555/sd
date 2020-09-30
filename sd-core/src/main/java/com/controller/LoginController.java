package com.controller;


import com.common.entity.SysMenu;
import com.common.enums.MenuTypeEnum;
import com.common.util.BeanUtil;
import com.controller.core.BaseController;
import com.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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

    @GetMapping("/userLogin")
    public String userLogin(){
        return "login";
    }
    @GetMapping("/")
    public String indexHtml(){
        return "redirect:/index";
    }


    @GetMapping("/index/system/main/welcome")
    public String homeHtml(){
        return "/system/main/welcome";
    }




    @GetMapping("/index")
    public String userIndex(Model model){

        List<SysMenu> sysMenus = menuServiceImpl.getListBySortOk();

        Map<String, SysMenu> sysMenuMap = sysMenus.stream().collect(Collectors.toMap(String::valueOf, Function.identity()));

        // 封装菜单树形数据
        Map<String, SysMenu> treeMenu = new HashMap<>(16);
        buildTreeMenu(sysMenuMap, treeMenu);


        model.addAttribute("treeMenu", treeMenu);
        return "index";
    }


    private void buildTreeMenu(Map<String, SysMenu> sysMenuMap, Map<String, SysMenu> treeMenu){

        sysMenuMap.forEach((k,v) -> {
            if (StringUtils.isEmpty(v.getPids())){
                treeMenu.put(k,v);
            }
        });

    }











}
