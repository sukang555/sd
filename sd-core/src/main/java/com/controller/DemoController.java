package com.controller;

import com.common.dto.ResponseBean;
import com.common.dto.StatusInfo;
import com.common.exception.BusinessException;
import com.controller.core.BaseController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sukang
 */
@RestController
@RequestMapping(value = "/public")
public class DemoController extends BaseController {

    @PostMapping(value = "/status-info")
    public ResponseBean getStatusInfo(
            @RequestBody @Validated StatusInfo statusInfo,
            BindingResult bindingResult){

        checkParams(bindingResult);

        if ("123".equals(statusInfo.getApplyNo())){
            throw new BusinessException("申请编号不存在");
        }
        return ResponseBean.ok("");
    }
}
