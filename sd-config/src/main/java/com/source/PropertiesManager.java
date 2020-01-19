package com.source;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author sukang
 */
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
@Setter
@Getter
public class PropertiesManager {

	@Value("${sd.active.env}")
	private String env;


	@Value("${sd.public.key}")
	private String publicKey;


	@Value("${sd.private.key}")
	private String privateKey;

	@Value("${query.ip.uri}")
	private String ipUri;

	@Value("${query.ip.token}")
	private String ipToken;
}
