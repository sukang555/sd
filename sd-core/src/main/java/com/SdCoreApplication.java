package com;


import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sukang
 */
@SpringBootApplication(
		scanBasePackages = {"com"},exclude = {DataSourceAutoConfiguration.class, PageHelperAutoConfiguration.class}

		)
@EnableConfigurationProperties
@EnableAsync
@MapperScan(basePackages = {"com.mapper"})
public class SdCoreApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SdCoreApplication.class)
				.build()
				.run(args);
	}
}

