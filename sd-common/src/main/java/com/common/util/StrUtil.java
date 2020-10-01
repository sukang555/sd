package com.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: sukang
 * @Date: 2020/10/1 15:16
 */
public class StrUtil {
    
    
    public static String idNoHide(String idNo){
        if (StringUtils.isBlank(idNo)){
            return idNo;
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < idNo.length() - 4; i++) {
            stringBuilder.append("*");
        }
        
        String substring = idNo.substring(idNo.length() - 4);
        return stringBuilder.append(substring).toString();
    }

    public static String nameHide(String name){
        if (StringUtils.isBlank(name)){
            return name;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < name.length() - 1; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.append(name.substring(name.length() - 1)).toString();
    }
}
