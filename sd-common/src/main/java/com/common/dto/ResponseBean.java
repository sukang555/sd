package com.common.dto;



import com.common.constant.RespStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author sukang
 */

@Setter
@Getter
public class ResponseBean {

    private Serializable body;

    private Integer code;

    private String errorMsg;

    public ResponseBean() {}

    public ResponseBean(Serializable body,Integer code, String errorMsg) {
        this.body = body;
        this.code = code;
        this.errorMsg = errorMsg;
    }


    /**
     *接口失败
     */
    public static ResponseBean paramError(String errorMsg){
        ResponseBeanBuilder builder = new ResponseBeanBuilder();
        return builder.body(null).errorMsg(errorMsg)
                .code(RespStatus.PARAM_ERROR_CODE).builder();
    }


    /**
     * 接口成功业务失败
     */
    public static ResponseBean failure(Serializable body){
        ResponseBeanBuilder builder = new ResponseBeanBuilder();
        return builder.body(body).errorMsg("")
                .code(RespStatus.ERROR_CODE).builder();
    }

    /**
     * 接口成功业务成功
     */
    public static ResponseBean ok(Serializable body){
        ResponseBeanBuilder builder = new ResponseBeanBuilder();

        return builder.body(body).errorMsg("")
                .code(RespStatus.SUCCESS_CODE).builder();
    }

}


/**
 * @author sukang
 */
class ResponseBeanBuilder {

    private String errorMsg;
    private Integer code;
    private Serializable body;


    public ResponseBeanBuilder errorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public ResponseBeanBuilder code(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseBeanBuilder body(Serializable body) {
        this.body = body;
        return this;
    }

    ResponseBean builder() {
        return new ResponseBean(body, code, errorMsg);
    }

}
