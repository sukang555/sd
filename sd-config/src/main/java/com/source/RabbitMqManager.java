package com.source;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.ManagedBean;

/**
 * @author sukang
 */

@ManagedBean
@PropertySource("classpath:sd.properties")
@ConfigurationProperties(prefix = "spring.rabbitmq.test")
public class RabbitMqManager {

    private String host;

    private String port;

    private String username;

    private String pwd;

    private String virtualHost;

    private String quene;

    public String getQuene() {
        return quene;
    }

    public void setQuene(String quene) {
        this.quene = quene;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RabbitMqManager{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", pwd='").append(pwd).append('\'');
        sb.append(", virtualHost='").append(virtualHost).append('\'');
        sb.append(", quene='").append(quene).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
