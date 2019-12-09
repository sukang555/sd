package com.service;

import com.common.dto.ResponseBean;
import com.dto.SdApplicationContext;

/**
 * @author sukang on 2019/12/9 14:35
 */
public interface EncryptionService {

    /**
     * 加密
     */
    public ResponseBean encryptText(SdApplicationContext applicationContext);


    public ResponseBean decryptText(SdApplicationContext applicationContext);
}
