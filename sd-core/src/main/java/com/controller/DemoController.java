package com.controller;

import com.common.dto.ResponseBean;
import com.common.dto.StatusInfo;
import com.common.exception.BusinessException;
import com.common.exception.Exceptions;
import com.controller.core.BaseController;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author sukang
 */
@RestController
@RequestMapping(value = "/public")
public class DemoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());



    @PostMapping(value = "/status-info")
    public ResponseBean getStatusInfo(
            @RequestBody @Validated StatusInfo statusInfo,
            BindingResult bindingResult){

        checkParams(bindingResult);

        if ("123".equals(statusInfo.getApplyNo())){
            throw Exceptions.newBusinessException("申请编号不存在");
        }
        logger.info(JSONObject.fromObject(statusInfo).toString());
        return ResponseBean.ok("");
    }
}
