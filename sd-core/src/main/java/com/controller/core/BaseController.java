package com.controller.core;

import com.common.exception.Exceptions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sukang
 */
public class BaseController {

    protected void checkParams(BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            List<String> collect = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            throw Exceptions.newPramException(collect.toString());
        }
    }

}
