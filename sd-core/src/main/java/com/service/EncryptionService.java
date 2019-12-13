package com.service;

import com.common.dto.ResponseBean;
import com.dto.EncryptDTO;
import com.dto.SdApplicationContext;

/**
 * @author sukang on 2019/12/9 14:35
 */
public interface EncryptionService {

    /**
     * 加密
     */
    public ResponseBean encryptText(SdApplicationContext<EncryptDTO> applicationContext);


    public ResponseBean decryptText(SdApplicationContext<EncryptDTO> applicationContext);
}
