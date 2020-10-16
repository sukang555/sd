package com.controller.core;


import com.common.dto.ResponseBean;
import com.common.exception.BusinessException;
import com.common.exception.ParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

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
        return ResponseBean.failure(ex.getMessage());
    }


    @ExceptionHandler(ParameterException.class)
    @ResponseBody
    public ResponseBean exceptionHandler(ParameterException ex){
        return ResponseBean.paramError(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseBean exceptionHandler(Exception ex, HttpServletRequest request){
        logger.error("请求路径为{},程序异常",request.getRequestURL(),ex);
        return ResponseBean.paramError("程序异常");
    }


}
