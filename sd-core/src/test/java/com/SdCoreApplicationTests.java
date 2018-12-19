package com;

import com.SdCoreApplication;
import com.common.entity.ScheduleJobEntity;
import com.common.util.BeanUtil;
import com.component.ApplicationUtils;
import com.service.ScheduleJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SdCoreApplication.class})
@ContextConfiguration
public class SdCoreApplicationTests {


	@Resource
	ScheduleJobService scheduleJobService;

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
		ApplicationUtils.getBeans(JdbcTemplate.class)
				.forEach((k,v) -> {System.out.println(k + ":"+v);});

		ApplicationUtils.getBeans(DataSource.class)
				.forEach((k,v) -> {System.out.println(k + ":"+v);});


	}

}
