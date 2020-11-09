package com.util;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author sukang
 * @date 2019 09 18
 */
public class WebClientUtil {

    private static  final Logger logger = LoggerFactory.getLogger(WebClientUtil.class);

    /**
     * 应用范围的webclient
     */
    private static WebClient webClient;

    static {
        SslContext sslContext = null;
        try {
            sslContext = SslContextBuilder
                    .forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
        } catch (SSLException e) {
            logger.error("创建webclient异常",e);
        }

        /*SslContext finalSslContext = sslContext;
        ClientHttpConnector httpConnector = new ReactorClientHttpConnector(opt -> {
            opt.(finalSslContext);
        });*/
        webClient = WebClient.builder().build();
    }




    public static <T> T doGet(String url,Map<String,String> header,
                              Map<String,String> uriParam,Class<T> clazz){
        WebClient.RequestBodySpec requestBodySpec = webClient.method(HttpMethod.GET)
                .uri(url)
                .attributes(t -> {
            t.putAll(uriParam);
        }).contentType(MediaType.APPLICATION_JSON_UTF8);

        return getResponse(requestBodySpec,clazz);
    }






    public static <T> T doPost(String url, Object reqBody,Class<T> clazz){
        return doPost(url,reqBody,clazz,null, MediaType.APPLICATION_JSON_UTF8);
    }

    public static <T> T doPost(String url, Object reqBody,Class<T> clazz,
                               Map<String,String> headers,MediaType mediaType){
        return postResponse(url,headers,reqBody,clazz, mediaType);
    }


    public static <T> T fromReq(String url, Map<String, String> header,
                                MultiValueMap<String,String> reqData, Class<T> clazz,
                                MediaType mediaType){

        WebClient.RequestBodySpec reqUrl = createReqUrl(url, HttpMethod.POST,mediaType,
                header);
        reqUrl.body(BodyInserters.fromFormData(reqData));
        return getResponse(reqUrl,clazz);
    }


    private static <T> T postResponse(String url, Map<String, String> header,
                                 Object reqData, Class<T> clazz,
                                 MediaType mediaType){
        WebClient.RequestBodySpec reqUrl = createReqUrl(
                url, HttpMethod.POST,mediaType,header);
        reqUrl.syncBody(reqData);
        return getResponse(reqUrl,clazz);
    }



    private static  <T> T getResponse(WebClient.RequestBodySpec requestBodyUriSpec,Class<T> clazz){
        return requestBodyUriSpec
                .retrieve()
                .bodyToMono(clazz)
                .block();
    }

    private static void addHeader(Map<String,String> header,
                                                   WebClient.RequestBodySpec reqUrl){
        if (header != null && !header.isEmpty()){
            header.forEach(reqUrl::header);
        }
    }

    private static WebClient.RequestBodySpec createReqUrl(String url,
                                                          HttpMethod method,
                                                          MediaType mediaType,
                                                          Map<String,String> header){

        Assert.isTrue(!StringUtils.isEmpty(url),"请求url不能为空");

        WebClient.RequestBodySpec requestBodySpec = webClient
                .method(method)
                .uri(url)
                .contentType(mediaType);

        addHeader(header,requestBodySpec);
        return requestBodySpec;
    }




    public static String buildUriParams(String uri,Map<String,String> params){
        StringBuilder builder = new StringBuilder(uri).append("?");
        params.forEach((k,v) -> builder.append(k).append("=").append(v).append("&"));
        return builder.toString();
    }

}
