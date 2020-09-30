package com.service;


import com.common.entity.SysMenu;

import java.util.List;


public interface MenuService {

    /**
     * 获取菜单列表数据
     * @return 菜单列表
     */
    List<SysMenu> getListBySortOk();

}
