package com.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 
 * 
 * @author sukang
 * 
 * @date 2019-12-11
 */
@Setter
@Getter
public class EncryptLog {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    private String plainText;

    private String encryptText;


    private String ipStr;

    private String ipInfo;

    private String type;

}