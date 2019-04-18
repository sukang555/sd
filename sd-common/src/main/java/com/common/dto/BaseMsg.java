package com.common.dto;

import com.common.util.BeanUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author sukang
 */
@Setter
@Getter
public class BaseMsg<T> implements Serializable {


    private final static String SUCCESS_CODE = "1000";

    private final static String FAILURE_CODE = "1001";

    private final static String SUCCESS_INFO = "success";


    private String code;

    private String msg;

    private T data;


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
        return BeanUtil.toJsonStr(this);
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
