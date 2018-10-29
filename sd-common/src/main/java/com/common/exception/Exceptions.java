package com.common.exception;

/**
 * @author sukang
 */
public class Exceptions {

    public static ParameterException newPramException(String errorInfo){
        return new ParameterException(errorInfo);
    }

    public static BusinessException newBusinessException(String errorInfo){
        return new BusinessException(errorInfo);
    }
}
