package com;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.ResponseBean;
import com.common.util.BeanUtil;
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
import org.springframework.aop.framework.ProxyFactory;

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
    public void main1(){

        String s2 = WebClientUtil.doPost("https://dsc.uat.bd.dk/dsc/api/dscOwnApi/findNoMachRuleDataByApplyNo",
                "1000297999", String.class);

        System.out.println(s2);

    }
}
