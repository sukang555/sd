package com;

import com.SdCoreApplication;
import com.common.entity.ScheduleJobEntity;
import com.common.util.BeanUtil;
import com.component.ApplicationUtils;
import com.component.BeanFacade;
import com.component.JobTask;
import com.config.RabbitMqConfigTest;
import com.config.RabbitMqConfigTest2;
import com.datasource.DataSourceNames;
import com.datasource.HandlerDataSource;
import com.mapper.ScheduleJobMapper;
import com.service.ScheduleJobService;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SdCoreApplication.class})
@ContextConfiguration
public class SdCoreApplicationTests {


	@Resource
	ScheduleJobService scheduleJobService;

	@Inject
	@Named("mySqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;




	@Test
	public void main5(){
        try {
            RedisTemplate localRedisTemplate = ApplicationUtils.getBean(
                    "localRedisTemplate2", RedisTemplate.class);

            HashOperations opsForHash = localRedisTemplate.opsForHash();

            for (int i = 0; i < 100; i++) {
                Double increment = opsForHash.increment("sukang",
                        "s1", new BigDecimal(0.01).doubleValue());
                System.out.println(increment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



	@Test
	public void main4(){
		try {
            ScheduleJobEntity scheduleJobEntity = scheduleJobService.getDataSourcePrimary(1l);
            System.out.println(BeanUtil.toJsonStr(scheduleJobEntity));


		}catch (Exception e){
			e.printStackTrace();
		}
	}



	@Test
	public void main3(){
		ScheduleJobEntity scheduleJobEntity = scheduleJobService.getFromDataSource(1l);
		System.out.println(BeanUtil.toJsonStr(scheduleJobEntity));
	}


	@Test
	public void main2(){

		ScheduleJobEntity scheduleJobEntity = scheduleJobService.getDataSourcePrimary(1l);
		System.out.println(BeanUtil.toJsonStr(scheduleJobEntity));

		scheduleJobEntity.setRemark("这是第二个数据源");
		Integer integer = scheduleJobService.updateEntity(scheduleJobEntity);

		System.out.println(integer);

	}





	@Test
	public void contextLoads() {
		RabbitMqConfigTest rabbitMqConfigTest = BeanFacade.getRabbitMqConfigTest();

		System.out.println(rabbitMqConfigTest);

		RabbitMqConfigTest2 rabbitMqConfigTest2 =
				BeanFacade.getRabbitMqConfigTest2();

		System.out.println(rabbitMqConfigTest2);

	}

}
