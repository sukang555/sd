package com.service;


import com.common.dto.BaseDTO;
import com.common.util.PageUtils;
import com.github.pagehelper.PageHelper;

import java.util.Map;

/**
 * @Author: sukang
 * @Date: 2020/10/1 14:01
 */
public interface BaseService {
    /**
     * 开始分页
     * @param paramMap 参数
     */
    default void startPage(Object object){
        BaseDTO startPage = PageUtils.startPage(object);
        PageHelper.startPage(startPage.getPageNum(), startPage.getPageSize());
    }
}
