package com.common.util;

import com.common.constant.CommonConstant;
import com.common.dto.BaseDTO;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;



/**
 * @Author: sukang
 * @Date: 2020/10/15 10:36
 */
public class PageUtils {

    public static BaseDTO startPage(Object object) {

        BaseDTO pageSizeDTO = new BaseDTO();
        int pageNum;
        int pageSize;

        try {
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(object, CommonConstant.PAGE_NUM);
            pageNum = Integer.parseInt(String.valueOf(propertyDescriptor.getReadMethod().invoke(object)));

            PropertyDescriptor propertyDescriptor2 = PropertyUtils.getPropertyDescriptor(object, CommonConstant.PAGE_SIZE);
            pageSize = Integer.parseInt(String.valueOf(propertyDescriptor2.getReadMethod().invoke(object)));

        } catch (Exception e) {
            pageNum = CommonConstant.DEFAULT_PAGE_NUM;
            pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
        }
        pageSizeDTO.setPageNum(pageNum);
        pageSizeDTO.setPageSize(pageSize);
        return pageSizeDTO;
    }

}
