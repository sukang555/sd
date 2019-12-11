package com.service.impl;

import com.common.dto.ResponseBean;
import com.common.entity.EncryptLog;
import com.common.util.EncryptUtils;
import com.component.BeanFacade;
import com.datasource.DynamicRouteDataSource;
import com.dto.EncryptDTO;
import com.dto.SdApplicationContext;
import com.mapper.EncryptLogMapper;
import com.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Date;


/**
 * @author sukang on 2019/12/9 15:05
 */
@Service
@DynamicRouteDataSource
public class EncryptionServiceImpl implements EncryptionService {

    @Resource
    private EncryptLogMapper encryptLogMapper;


    @Override
    public ResponseBean encryptText(SdApplicationContext applicationContext) {
        EncryptDTO encryptDTO = (EncryptDTO)applicationContext.getData();
        String encrypt = EncryptUtils.encryptByPublicKey(encryptDTO.getPlaintext());

        EncryptLog encryptLog = new EncryptLog();
        encryptLog.setCreateTime(new Date());
        encryptLog.setPlainText(encryptDTO.getPlaintext());
        encryptLogMapper.insert(encryptLog);

        return ResponseBean.ok(encrypt);
    }



    @Override
    public ResponseBean decryptText(SdApplicationContext applicationContext) {
        EncryptDTO encryptDTO = (EncryptDTO)applicationContext.getData();

        String decrypt = EncryptUtils.decryptByPrivateKey(encryptDTO.getPlaintext());

        EncryptLog encryptLog = new EncryptLog();
        encryptLog.setCreateTime(new Date());
        encryptLog.setPlainText(decrypt);
        encryptLogMapper.insert(encryptLog);
        return ResponseBean.ok(decrypt);
    }
}
