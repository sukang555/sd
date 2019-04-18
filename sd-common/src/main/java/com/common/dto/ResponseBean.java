package com.common.dto;



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

    private boolean hasError;

    private String errorMsg;

    public ResponseBean() {}

    public ResponseBean(Serializable body, boolean hasError, String errorMsg) {
        this.body = body;
        this.hasError = hasError;
        this.errorMsg = errorMsg;
    }


    /**
     *接口失败
     */
    public static ResponseBean paramError(String errorMsg){
        ResponseBeanBuilder builder = new ResponseBeanBuilder();
        return builder.body(null).errorMsg(errorMsg)
                .hasError(true).builder();
    }


    /**
     * 接口成功业务失败
     */
    public static ResponseBean failure(BaseMsg body){
        ResponseBeanBuilder builder = new ResponseBeanBuilder();
        return builder.body(body).errorMsg("")
                .hasError(false).builder();
    }

    /**
     * 接口成功业务成功
     */
    public static ResponseBean ok(Serializable body){
        ResponseBeanBuilder builder = new ResponseBeanBuilder();

        return builder.body(BaseMsg.success(body)).errorMsg("")
                .hasError(false).builder();
    }

}


/**
 * @author sukang
 */
class ResponseBeanBuilder {

    private String errorMsg;
    private boolean hasError;
    private Serializable body;


    public ResponseBeanBuilder errorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public ResponseBeanBuilder hasError(boolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public ResponseBeanBuilder body(Serializable body) {
        this.body = body;
        return this;
    }

    ResponseBean builder() {
        return new ResponseBean(body, hasError, errorMsg);
    }

}
