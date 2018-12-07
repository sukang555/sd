package sd.core;

import com.HelloAdvice;
import com.HelloService;
import com.SdCoreApplication;
import com.common.entity.ScheduleJobEntity;
import com.common.util.BeanUtil;
import com.component.ApplicationUtils;
import com.mapper.ScheduleJobMapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SdCoreApplication.class})
@ContextConfiguration
public class SdCoreApplicationTests {


	@Resource
	ScheduleJobMapper scheduleJobMapper;




	@Test
	public void main2(){

		ScheduleJobEntity scheduleJobEntity = scheduleJobMapper.selectByPrimaryKey(1l);
		System.out.println(BeanUtil.fromObjectToStr(scheduleJobEntity));

		scheduleJobEntity.setRemark("这是第二个数据源插入的数据");
		int i = scheduleJobMapper.updateByPrimaryKey(scheduleJobEntity);

		System.out.println("i++++++"+i);
	}





	@Test
	public void contextLoads() {
		ApplicationUtils.getBeans(JdbcTemplate.class)
				.forEach((k,v) -> {System.out.println(k + ":"+v);});

		ApplicationUtils.getBeans(DataSource.class)
				.forEach((k,v) -> {System.out.println(k + ":"+v);});


	}

}
