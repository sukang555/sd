package com.controller;

import com.common.dto.ResponseBean;
import com.controller.core.BaseController;
import com.dto.EncryptDTO;
import com.dto.SdApplicationContext;
import com.service.EncryptionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author sukang on 2019/12/9 14:26
 */
@RestController
@RequestMapping(value = "/public/encryption")
public class EncryptController extends BaseController {

    @Resource
    private EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseBean encryptStr(@RequestBody @Validated EncryptDTO encryptDTO,
                                   BindingResult bindingResult,
                                   HttpServletRequest servletRequest){

        checkParams(bindingResult);

        SdApplicationContext<EncryptDTO> applicationContext = SdApplicationContext.
                <EncryptDTO>getBuilder()
                .setData(encryptDTO)
                .setHttpServletRequest(servletRequest).builder();
        return encryptionService.encryptText(applicationContext);
    }

    @PostMapping("/decrypt")
    public ResponseBean decryptStr(@RequestBody @Validated EncryptDTO encryptDTO,
                                   BindingResult bindingResult,
                                   HttpServletRequest servletRequest){
        checkParams(bindingResult);

        SdApplicationContext<EncryptDTO> applicationContext = SdApplicationContext.
                <EncryptDTO>getBuilder()
                .setData(encryptDTO)
                .setHttpServletRequest(servletRequest).builder();
        return encryptionService.decryptText(applicationContext);
    }

}
