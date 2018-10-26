package com.controller.core;

import com.common.exception.ParamterException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
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
            throw new ParamterException(collect.toString());
        }
    }


}
