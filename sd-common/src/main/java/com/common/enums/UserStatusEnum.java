package com.common.enums;


import lombok.Getter;

/**
 * @Author: sukang
 * @Date: 2020/10/1 16:45
 */
@Getter
public enum  UserStatusEnum {

    /**
     *
     */
    NORMAL_STATUS(1,"正常状态"),
    /**
     *
     */
    EXPIRE_STATUS(2,"失效状态")
    ;


    private Integer status;

    private String desc;


    UserStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
