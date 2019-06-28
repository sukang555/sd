package com;

import com.SdCoreApplication;
import com.common.entity.ScheduleJobEntity;
import com.common.util.BeanUtil;
import com.component.ApplicationUtils;
import com.component.BeanFacade;
import com.component.JobTask;
import com.config.RabbitMqConfigTest;
import com.config.RabbitMqConfigTest2;
import com.mapper.ScheduleJobMapper;
import com.service.ScheduleJobService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
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
	//@Named("mySqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;


	@Inject
	//@Named("mySqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;



	@Test
	public void main5(){
        try {
            RedisTemplate localRedisTemplate = ApplicationUtils.getBean(
                    "localRedisTemplate", RedisTemplate.class);

            HashOperations opsForHash = localRedisTemplate.opsForHash();

            List sukang = opsForHash.values("sukang");

            System.out.println(BeanUtil.toJsonStr(sukang));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



	@Test
	public void main4(){
		try {

			for (int i = 0; i < 10; i++) {
				ScheduleJobEntity scheduleJobEntity = sqlSessionTemplate.getMapper(
						ScheduleJobMapper.class).selectByPrimaryKey(1l);
				System.out.println(BeanUtil.toJsonStr(scheduleJobEntity));

			}

		}catch (Exception e){
			e.printStackTrace();
		}

	/*	ScheduleJobEntity scheduleJobEntity2 = scheduleJobService.getFromDataSource(1l);
		System.out.println(BeanUtil.toJsonStr(scheduleJobEntity2));*/
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
