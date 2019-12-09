package com.dto;

import javax.servlet.http.HttpServletRequest;




/**
 * @author sukang on 2019/12/9 14:38
 */
public class SdApplicationContext<T> {

    private HttpServletRequest httpServletRequest;

    private T data;

    public SdApplicationContext() {
    }


    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Builder<T> getBuilder(){
        return new SdApplicationContext.Builder<T>();
    }


    public static class Builder<T> {

        private HttpServletRequest httpServletRequest;

        private T object;

        private Class clazz;

        public Builder<T> setData(T object){
            this.object = object;
            return this;
        }

        public Builder setServletRequest(HttpServletRequest httpServletRequest){
            this.httpServletRequest = httpServletRequest;
            return this;
        }

        public SdApplicationContext<T> builder(){
            SdApplicationContext<T> applicationContext = new SdApplicationContext<>();
            applicationContext.setData(this.object);
            applicationContext.setHttpServletRequest(this.httpServletRequest);
            return applicationContext;
        }

    }

}
