package com.service.impl;


import com.common.dto.ResponseBean;
import com.common.entity.EncryptLog;
import com.util.IpUtils;
import com.util.EncryptUtils;
import com.datasource.DataSourceNames;
import com.datasource.DynamicRouteDataSource;
import com.dto.EncryptDTO;
import com.dto.SdApplicationContext;
import com.mapper.EncryptLogMapper;
import com.service.EncryptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
            return ResponseBean.failure("加密异常");
        }


        EncryptLog encryptLog = new EncryptLog();
        encryptLog.setCreateTime(new Date());
        encryptLog.setPlainText(encryptDTO.getPlaintext());
        encryptLog.setType("1");
        encryptLog.setIpStr(IpUtils.getIP(applicationContext.getHttpServletRequest()));

        encryptLog.setIpInfo(IpUtils.query(encryptLog.getIpStr()));
        encryptLogMapper.insert(encryptLog);

        return ResponseBean.ok(encrypt);
    }



    @Override
    public ResponseBean decryptText(SdApplicationContext<EncryptDTO> applicationContext) {
        EncryptDTO encryptDTO = applicationContext.getData();

        String decrypt = EncryptUtils.decryptByPrivateKey(encryptDTO.getPlaintext());

        if (StringUtils.isBlank(decrypt)){
            return ResponseBean.failure("解密异常");
        }


        EncryptLog encryptLog = new EncryptLog();
        encryptLog.setCreateTime(new Date());
        encryptLog.setPlainText(decrypt);
        encryptLog.setType("2");
        encryptLog.setIpStr(IpUtils.getIP(applicationContext.getHttpServletRequest()));
        encryptLog.setIpInfo(IpUtils.query(encryptLog.getIpStr()));
        encryptLogMapper.insert(encryptLog);
        return ResponseBean.ok(decrypt);
    }
}
