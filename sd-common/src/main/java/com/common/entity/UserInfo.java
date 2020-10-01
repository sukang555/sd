package com.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * @author sukang
 * 
 * @date 2020-10-01
 */
@Setter
@Getter
public class UserInfo {
    /**
     * 主键
     */
    private Integer id;

    private Integer userId;

    /**
     * 头像地址
     */
    private String headImage;

    /**
     * 用户名 或者昵称
     */
    private String userName;

    /**
     * 身份证
     */
    private String idNo;

    /**
     * 身份证姓名
     */
    private String idName;
}