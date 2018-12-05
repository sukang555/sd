package com;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sukang
 */
@SpringBootApplication(
		scanBasePackages = {"com"}

		)
@EnableConfigurationProperties
@EnableAsync
public class SdCoreApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SdCoreApplication.class)
				.build()
				.run(args);
	}
}

