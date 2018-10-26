package com.common.dto;

import java.io.Serializable;

/**
 * @author sukang
 */
public class BaseMsg<T> implements Serializable {


    private final static String SUCCESS_CODE = "1000";

    private final static String FAILURE_CODE = "1001";

    private final static String SUCCESS_INFO = "success";


    private String code;

    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseMsg(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseMsg{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    public static BaseMsg success(Serializable data) {
        return new BaseMsg(BaseMsg.SUCCESS_CODE, BaseMsg.SUCCESS_INFO, data);
    }

    public static BaseMsg failure(Serializable data, String failureInfo) {

        if (data instanceof String){
            return new BaseMsg<>(BaseMsg.FAILURE_CODE,
                    failureInfo, (String) data);
        }




        return new  BaseMsg<>(BaseMsg.FAILURE_CODE,
                failureInfo,data);
    }

}
