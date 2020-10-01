package com.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: sukang
 * @Date: 2020/10/1 15:05
 */
@Getter
@Setter
public class UserInfoDTO {

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
