package com;


import com.common.entity.ScheduleJobEntity;
import com.common.util.BeanUtil;
import com.component.ApplicationUtils;
import com.component.BeanFacade;

import com.component.RedisDistributedLock;


import com.service.ScheduleJobService;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SdCoreApplication.class})
@ContextConfiguration
@Slf4j
public class SdCoreApplicationTests {


	@Resource
	ScheduleJobService scheduleJobService;

	@Inject
	@Named("localRedisTemplate2")
	private RedisTemplate redisTemplate;

	@Inject
	@Named("mySqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;


	@Resource
	private RedisDistributedLock redisDistributedLock;



	@Resource
	private JdbcTemplate primaryJdbcTemplate;

	@Resource
	private JdbcTemplate secondJdbcTemplate;


	@Test
	public void mainJdbc(){
		List<Map<String, Object>> queryForList = secondJdbcTemplate.queryForList("SELECT * from encrypt_log");


		System.out.println(BeanUtil.toJsonStr(queryForList));
	}



	@Test
	public void mainZK(){
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
		CuratorFramework client = CuratorFrameworkFactory
				.builder()
				.connectString("47.105.107.249:2181")
				.sessionTimeoutMs(10000)
				.retryPolicy(retryPolicy)
				.namespace("admin")
				.build();



	}





	@Test
	public void mainLuna(){

		String lockKey = "ssuuLock";
		String token = UUID.randomUUID().toString().replace("-", "");

		try {
			Boolean lockSuccess = redisDistributedLock.lock(lockKey, TimeUnit.SECONDS,
					300, token);

			if (lockSuccess){
				log.info("{}锁token:{}获取锁成功",lockKey,token);
				TimeUnit.SECONDS.sleep(30);


				Boolean ssuuLock = redisDistributedLock.unLock(lockKey, token);
				if (ssuuLock){
					log.info("{}锁token:{}释放成功",lockKey, token);
				}else {
					log.info("{}锁token:{}释放失败");
				}
			}else {
				log.info("{}锁token:{}获取锁失败",lockKey,token);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


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


}
