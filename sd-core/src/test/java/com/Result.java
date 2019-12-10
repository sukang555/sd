package com;

import com.common.util.BeanUtil;
import com.dto.EncryptDTO;
import org.junit.Test;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private int status;
    private String message = "";
    private T data;
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Result() {
    }
    private Result(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public static Result.ResultBuiler builder(){
        return new Result.ResultBuiler();
    }
    public static class ResultBuiler<T>{
        private int status;
        private String message;
        private T data;
        public Result.ResultBuiler status(int status){
            this.status = status;
            return this;
        }
        public Result.ResultBuiler message(String message){
            this.message = message;
            return this;
        }
        public Result.ResultBuiler data(T data){
            this.data = data;
            return this;
        }
        public Result build(){
            return new Result(this.status,this.message,this.data);
        }
    }


    public static void main(String[] args) {
        EncryptDTO encryptDTO = new EncryptDTO();
        encryptDTO.setPlaintext("hello");
        Result<EncryptDTO> success = Result.<EncryptDTO>builder().status(200)
                .message("success").data(encryptDTO).build();
        System.out.println(BeanUtil.toJsonStr(success));
    }
}
