package com.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author: sukang
 * @Date: 2020/10/1 15:05
 */
@Getter
@Setter
public class UserInfoDTO extends BaseDTO{

    /**
     * 主键
     */
    private Integer id;

    /**
     * user表id
     */
    private Integer userId;

    /**
     * 头像地址
     */
    private String headImage;

    private Integer status;

    private String sex;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 证件上的姓名
     */
    private String idName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 紧急联系人名称
     */
    private String emergencyName;

    /**
     * 紧急联系人电话
     */
    private String emergencyPhone;

    /**
     * 与紧急联系人的关系
     */
    private String emergencyRelation;


    private LocalDateTime createTime = LocalDateTime.now();
}
