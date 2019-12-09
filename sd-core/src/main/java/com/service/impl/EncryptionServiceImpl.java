package com.service.impl;

import com.common.dto.ResponseBean;
import com.common.util.EncryptUtils;
import com.dto.EncryptDTO;
import com.dto.SdApplicationContext;
import com.service.EncryptionService;
import org.springframework.stereotype.Service;


/**
 * @author sukang on 2019/12/9 15:05
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {


    @Override
    public ResponseBean encryptText(SdApplicationContext applicationContext) {
        EncryptDTO encryptDTO = (EncryptDTO)applicationContext.getData();

        String encrypt = EncryptUtils.encryptByPublicKey(encryptDTO.getPlaintext());

        return ResponseBean.ok(encrypt);
    }



    @Override
    public ResponseBean decryptText(SdApplicationContext applicationContext) {
        EncryptDTO encryptDTO = (EncryptDTO)applicationContext.getData();

        String decrypt = EncryptUtils.decryptByPrivateKey(encryptDTO.getPlaintext());

        return ResponseBean.ok(decrypt);
    }
}
