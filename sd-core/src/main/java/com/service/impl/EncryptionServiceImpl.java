package com.service.impl;

import com.common.dto.BaseMsg;
import com.common.dto.ResponseBean;
import com.common.entity.EncryptLog;
import com.common.util.EncryptUtils;
import com.component.BeanFacade;
import com.datasource.DataSourceNames;
import com.datasource.DynamicRouteDataSource;
import com.datasource.HandlerDataSource;
import com.dto.EncryptDTO;
import com.dto.SdApplicationContext;
import com.mapper.EncryptLogMapper;
import com.service.EncryptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Date;


/**
 * @author sukang on 2019/12/9 15:05
 */
@Service
@DynamicRouteDataSource(DataSourceNames.FIRST)
public class EncryptionServiceImpl implements EncryptionService {

    @Resource
    private EncryptLogMapper encryptLogMapper;


    @Override
    public ResponseBean encryptText(SdApplicationContext<EncryptDTO> applicationContext) {
        EncryptDTO encryptDTO = applicationContext.getData();
        String encrypt = EncryptUtils.encryptByPublicKey(encryptDTO.getPlaintext());

        if (StringUtils.isBlank(encrypt)){
            return ResponseBean.failure(BaseMsg.failure(null,"加密异常"));
        }

        EncryptLog encryptLog = new EncryptLog();
        encryptLog.setCreateTime(new Date());
        encryptLog.setPlainText(encryptDTO.getPlaintext());
        encryptLogMapper.insert(encryptLog);

        return ResponseBean.ok(encrypt);
    }



    @Override
    public ResponseBean decryptText(SdApplicationContext<EncryptDTO> applicationContext) {
        EncryptDTO encryptDTO = applicationContext.getData();

        String decrypt = EncryptUtils.decryptByPrivateKey(encryptDTO.getPlaintext());

        if (StringUtils.isBlank(decrypt)){
            return ResponseBean.failure(BaseMsg.failure(null,"解密异常"));
        }

        EncryptLog encryptLog = new EncryptLog();
        encryptLog.setCreateTime(new Date());
        encryptLog.setPlainText(decrypt);
        encryptLogMapper.insert(encryptLog);
        return ResponseBean.ok(decrypt);
    }
}
