package sd.core;

import com.common.util.WebClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/22.
 */
public class DaliyTest {

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
