package com.common.exception;

import org.jetbrains.annotations.NonNls;

/**
 * @author sukang
 */
public class ParamterException extends RuntimeException{

    public ParamterException() {
    }

    public ParamterException( String message) {
        super(message);
    }

    public ParamterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamterException(Throwable cause) {
        super(cause);
    }

    public ParamterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
