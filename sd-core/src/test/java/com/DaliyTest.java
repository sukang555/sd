package com;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.ResponseBean;
import com.common.util.BeanUtil;
import com.common.util.CheckSumBuilder;
import com.core.component.HelloAdvice;
import com.core.component.HelloService;
import com.dto.StatusInfo;
import com.google.common.collect.Maps;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.util.WebClientUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.quartz.simpl.SystemPropertyInstanceIdGenerator;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.print.DocFlavor;
import java.util.*;

/**
 * @author sukang  on 2018/7/22.
 */
public class DaliyTest {



    @Test
    public void main6(){

        for (int i = 0; i < 100; i++) {
            String s = UUID.randomUUID().toString();

            System.out.println(s.replaceAll("-",""));
        }
    }


    @Test
    public void main5(){

        String s = Base64.getEncoder().encodeToString("sukang".getBytes());
        byte[] bytes = Base64.getDecoder().decode(s);

        System.out.println(s);
    }


    @Test
    public void main4(){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("47.105.107.249");
            factory.setUsername("test");
            factory.setPassword("test");
            factory.setPort(5672);
            factory.setVirtualHost("/test");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String message = "{\"applyNo\":\"2021846\",\"code\":\"100000\"," +
                    "\"link\":\"REQUEST_FUNDS\",\"message\":\"success\"," +
                    "\"needSupply\":true} ";


            channel.basicPublish("amq.direct", "demo", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Test
    public void main2(){
        HelloService helloService = new HelloService();

        ProxyFactory proxyFactory = new ProxyFactory(helloService);

        proxyFactory.addAdvice(new HelloAdvice());

        HelloService proxy = (HelloService) proxyFactory.getProxy();
        ResponseBean canshu = proxy.pushData(null);

        System.out.println(BeanUtil.toJsonStr(canshu));

   }

    @Test
    public void main3() {
       /* ScheduleJobEntity jobEntity = new ScheduleJobEntity();
        jobEntity.setJobId(null);
        jobEntity.setBeanName("test");
        jobEntity.setCreateTime(new Date());
        jobEntity.setIsDelete(false);

        net.sf.json.JSONObject jsonObject = BeanUtil.formObjectToJson(jobEntity);

        jsonObject.forEach((key, value) -> {
            System.out.println(key +"--"+ value);
        });
        String s = BeanUtil.fromObjectToStr(jobEntity);
        print(s);
        ScheduleJobEntity scheduleJobEntity = BeanUtil.fromStrToObj(s, ScheduleJobEntity.class);
        System.out.println(scheduleJobEntity.toString());*/
    }











    private static void print(String string){
        System.out.println(string);
    }


    @Test
    public void main21(){


        Map<String, String> headerMap = new HashMap<>(4);
        headerMap.put("AppKey","b08d4bbdaff736fdeb10924f1ec0496e");
        headerMap.put("Nonce",String.valueOf(System.nanoTime()));
        headerMap.put("CurTime",String.valueOf(System.currentTimeMillis()/1000));
        headerMap.put("CheckSum", CheckSumBuilder.getCheckSum(
                "400ef62f7905",headerMap.get("Nonce"),headerMap.get("CurTime")));

        MultiValueMap<String, String> reqBody = new LinkedMultiValueMap<>();

        final String mobile = "13271357065";
        reqBody.add("mobile",mobile);
        reqBody.add("authCode","0501");

        System.out.println(BeanUtil.toJsonStr(headerMap));



        String s = WebClientUtil.fromReq(
                "https://api.netease.im/sms/sendcode.action",
                headerMap,
                reqBody,
                String.class,
                MediaType.APPLICATION_FORM_URLENCODED,
                null
        );

        System.out.println(s);
    }



    @Test
    public void main1(){

        Map<String, Object> map = new HashMap<>();

        map.put("mobile","17600690562");
        map.put("codeLen","1234");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("AppKey","b08d4bbdaff736fdeb10924f1ec0496e");
        httpHeaders.add("Nonce","");
        httpHeaders.add("CurTime","");
        httpHeaders.add("CheckSum","");

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(
                "https://api.netease.im/sms/sendcode.action",
                HttpMethod.POST, httpEntity, String.class, map);

        System.out.println(exchange.getBody());

    }
}
