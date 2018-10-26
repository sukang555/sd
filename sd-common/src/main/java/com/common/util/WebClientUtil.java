package com.common.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;


/**
 * @author sukang
 */
public class WebClientUtil {

    private static  final Logger logger = LoggerFactory.getLogger(WebClientUtil.class);



    public static <T> T doPost(String url, Object reqBody, Class<T> clazz){

        return postReq(url,null,reqBody,clazz,null);
    }

    public static <T> T doPost(String url, Object reqBody,
                               Class<T> clazz,
                               Map<String,String> headers){

        return postReq(url,headers,reqBody,clazz,null);
    }

    public static <T> T doPost(String url, Object reqBody, Class<T> clazz,
                               Map<String,String> headers,
                               Map<String,String> params){
        return postReq(url,headers,reqBody,clazz,params);
    }


    public static <T> T doGet(String url,
                              Map<String,String> header,
                              Map<String,String> params,
                              Class<T> clazz){

        return  getReq(url,header,params,clazz);
    }


    public static <T> T doGet(String url, Class<T> clazz){

        return getReq(url,null,null,clazz);
    }


    public static  <T> T doGet(String url, Map<String,String> params, Class<T> clazz){

        return getReq(url,null,params,clazz);
    }

    private static <T> T getReq(String url, Map<String,String> header,
                                Map<String,String> params,
                                Class<T> clazz){
        return getResponse(createReqUrl(url, HttpMethod.GET,header,params),
                MediaType.APPLICATION_JSON_UTF8,
                clazz);
    }


    private static <T> T postReq(String url, Map<String,String> header,
                                 Object reqData, Class<T> clazz,
                                 Map<String,String> params){

        WebClient.RequestBodySpec reqUrl = createReqUrl(url, HttpMethod.POST,header,params);
        reqUrl.body(Mono.just(reqData),Object.class);
        return getResponse(reqUrl,MediaType.APPLICATION_JSON_UTF8,clazz);
    }

    private static  <T> T getResponse(WebClient.RequestBodySpec requestBodyUriSpec,
                                      MediaType mediaType, Class<T> clazz){
        return requestBodyUriSpec
                .accept(mediaType)
                .retrieve()
                .bodyToMono(clazz)
                .block();
    }



    private static WebClient.RequestBodySpec addHeader(Map<String,String> header,
                                                   WebClient.RequestBodySpec reqUrl){
        if (header != null && !header.isEmpty()){
            header.forEach(reqUrl::cookie);
        }
        return reqUrl;
    }

    private static WebClient.RequestBodySpec createReqUrl(String url,
                                                          HttpMethod method,
                                                          Map<String,String> header,
                                                          Map<String,String> params){
        Assert.isTrue(!StringUtils.isEmpty(url),"请求url不能为空");

        logger.info("发送http请求的url为{},url参数为{}",url,isJson(params));
        WebClient.RequestBodySpec requestBodySpec;
        if (method.equals(HttpMethod.GET) && params != null){
            requestBodySpec = url(method).uri(url, params);
        }else {
            requestBodySpec = url(method).uri(url);
        }
        addHeader(header,requestBodySpec);
        return requestBodySpec;
    }


    private static WebClient.RequestBodyUriSpec url(HttpMethod method){
        return WebClient.create().method(method);
    }

    private static String isJson(Object reqData){

        try {
            return JSONObject.fromObject(reqData).toString();
        }catch (Exception e){
            logger.error("json转换异常",e);
        }
        return "null";
    }



}
