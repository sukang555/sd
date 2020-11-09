package com.source;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author sukang
 */
@Configuration
@Setter
@Getter
public class PropertiesManager {

	@Value("${spring.profiles.active}")
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
