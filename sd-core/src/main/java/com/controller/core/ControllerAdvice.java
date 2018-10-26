package com.controller.core;

import com.common.dto.BaseMsg;
import com.common.dto.ResponseBean;
import com.common.exception.BusinessException;
import com.common.exception.ParamterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sukang
 */
@Component
@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = {"com.controller"})
public class ControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseBean exceptionHandler(BusinessException ex){
        return ResponseBean.failure(BaseMsg.failure("",ex.getMessage()));
    }


    @ExceptionHandler(ParamterException.class)
    @ResponseBody
    public ResponseBean exceptionHandler(ParamterException ex){
        return ResponseBean.paramError(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean exceptionHandler(Exception ex){
        return ResponseBean.paramError("程序异常");
    }


}
