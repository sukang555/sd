package com;

import com.util.WebClientUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sukang  on 2018/7/22.
 */
public class DaliyTest {






    @Test
    public void main5(){
       try {



       }catch (Exception e){

       }
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
        /*HelloService helloService = new HelloService();

        ProxyFactory proxyFactory = new ProxyFactory(helloService);

        proxyFactory.addAdvice(new HelloAdvice());

        HelloService proxy = (HelloService) proxyFactory.getProxy();
        ResponseBean canshu = proxy.pushData(null);

        System.out.println(BeanUtil.fromObjectToStr(canshu));*/


        System.out.println(Base64.getDecoder().decode("eyJhcHBseU5vIjoiMjAyMTg0NiIsImNvZGUiOiIxMDAwMDAiLCJsaW5rIjoiUkVRVUVTVF9GVU5EUyIsIm1lc3NhZ2UiOiJzdWNjZXNzIiwibmVlZFN1cHBseSI6dHJ1ZX0g".getBytes()));
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
        Map<String,String> params = new HashMap<>();
        params.put("name","sukang");

        String s1 = WebClientUtil.doGet("http://localhost:18080/public/json/name",
                params,String.class);



        String s2 = WebClientUtil.doPost("http://localhost:18080/public/json/name1", params, String.class);

       String s3 = WebClientUtil.doGet("http://localhost:18080/public/json/name3", params, String.class);


       String htmlStr = WebClientUtil.doGet("https://www.skcore.cn/", String.class);


        System.out.println(htmlStr);

    }
}
