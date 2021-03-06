package com.service.impl;


import com.common.constant.CommonConstant;
import com.common.entity.SysMenu;
import com.datasource.DynamicRouteDataSource;
import com.mapper.SysMenuMapper;
import com.service.BaseService;
import com.service.MenuService;
;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author sukan
 */
@Service
@DynamicRouteDataSource
public class MenuServiceImpl implements MenuService, BaseService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getListBySortOk() {
        return sysMenuMapper.selectAllByStatus(CommonConstant.NORMAL);
    }













}
