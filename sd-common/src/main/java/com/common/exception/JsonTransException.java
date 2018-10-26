package com.common.exception;

/**
 * @author sukang
 */
public class JsonTransException extends  RuntimeException {

    public JsonTransException(String message) {
        super(message);
    }

    public JsonTransException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonTransException(Throwable cause) {
        super(cause);
    }
}
