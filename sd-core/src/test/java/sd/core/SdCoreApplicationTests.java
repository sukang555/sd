package sd.core;

import com.HelloAdvice;
import com.HelloService;
import com.SdCoreApplication;
import com.component.ApplicationUtils;
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

import javax.inject.Inject;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SdCoreApplication.class})
@ContextConfiguration
public class SdCoreApplicationTests {













	@Test
	public void contextLoads() {
		ApplicationUtils.getBeans(JdbcTemplate.class)
				.forEach((k,v) -> {System.out.println(k + ":"+v);});

		ApplicationUtils.getBeans(DataSource.class)
				.forEach((k,v) -> {System.out.println(k + ":"+v);});


	}

}
