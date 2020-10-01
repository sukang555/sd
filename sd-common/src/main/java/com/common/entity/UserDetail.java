package com.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * @author sukang
 * 
 * @date 2020-09-29
 */
@Setter
@Getter
public class UserDetail {
    private Integer id;

    private String username;

    private String password;

    private Integer status;

    private String descn;


}