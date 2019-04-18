package com.source;

import com.common.util.BeanUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.ManagedBean;

/**
 * @author sukang
 */

@Component
@PropertySource("classpath:sd.properties")
@ConfigurationProperties(prefix = "spring.rabbitmq.test")
@Getter
@Setter
public class RabbitMqManager {

    private String host;

    private String port;

    private String username;

    private String pwd;

    private String virtualHost;

    private String quene;

    @Override
    public String toString() {
        return BeanUtil.toJsonStr(this);
    }
}
