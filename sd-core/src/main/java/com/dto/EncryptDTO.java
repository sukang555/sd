package com.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author sukang on 2019/12/9 14:28
 */
public class EncryptDTO {

    @NotBlank(message = "明文不能为空")
    private String plaintext;

    public String getPlaintext() {
        return plaintext.trim();
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
}
