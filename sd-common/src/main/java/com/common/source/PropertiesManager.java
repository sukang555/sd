package com.common.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration

/**
 * 如果加在启动类中，属性注入到environment中
 * 加在bean中，通过@value注解来获取
 */
@PropertySources({
	@PropertySource("classpath:sd.properties")
	})
/**
 * @ConfigurationProperties(prefix = "sd.active")
 */

public class PropertiesManager {

	@Value("${sd.active.env}")
	private String env;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}
	
	


}
