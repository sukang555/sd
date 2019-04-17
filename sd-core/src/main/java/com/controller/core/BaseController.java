package com.controller.core;

import com.common.dto.ResponseBean;
import com.common.exception.Exceptions;
import com.common.util.BeanUtil;
import com.dto.StatusInfo;
import com.util.WebClientUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sukang
 */
public class BaseController {



    public void checkParams(BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            List<String> collect = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            throw Exceptions.newPramException(collect.toString());
        }
    }

}
