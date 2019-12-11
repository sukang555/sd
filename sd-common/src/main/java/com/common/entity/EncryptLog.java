package com.common.entity;

import java.util.Date;

/**
 * 
 * 
 * @author sukang
 * 
 * @date 2019-12-11
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText == null ? null : plainText.trim();
    }

    public String getEncryptText() {
        return encryptText;
    }

    public void setEncryptText(String encryptText) {
        this.encryptText = encryptText == null ? null : encryptText.trim();
    }
}