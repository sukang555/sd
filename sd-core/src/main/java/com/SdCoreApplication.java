package com;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class SdCoreApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SdCoreApplication.class)
				.build()
				.run(args);
	}
}

