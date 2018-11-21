package com.config;

import com.common.source.RabbitMqManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;

/**
 * @author sukang
 */
@Configuration
public class RabbitMqConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    private RabbitMqManager rabbitMqManager;

    @Autowired
    public RabbitMqConfig(RabbitMqManager rabbitMqManager) {
        this.rabbitMqManager = rabbitMqManager;
    }

    @Bean(name = "testConnectionFactory")
    public ConnectionFactory getConnectFactory(){

        logger.info("testConnectionFactory 连接信息为{}",rabbitMqManager);
        //创建rabbitmq连接工厂
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(rabbitMqManager.getHost());
        cachingConnectionFactory.setPort(Integer.parseInt(rabbitMqManager.getPort()));
        cachingConnectionFactory.setVirtualHost("/".concat(rabbitMqManager.getVirtualHost()));
        cachingConnectionFactory.setUsername(rabbitMqManager.getUsername());
        cachingConnectionFactory.setPassword(rabbitMqManager.getPwd());
        cachingConnectionFactory.addChannelListener((v,t) -> {
            logger.info("{} shutdown",rabbitMqManager.toString());
        });

        cachingConnectionFactory.addConnectionListener((t) -> {
            logger.info("{} create",rabbitMqManager.toString());
        });
        return cachingConnectionFactory;
    }

    @Bean(name = "testSimpleMessageListenerContainer")
    public SimpleMessageListenerContainer testListenerContainer(
            @Named("testConnectionFactory") ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(rabbitMqManager.getQuene().split(","));
        container.setMessageListener(new RabbitMqConsumer());
        container.setMessageConverter(new Jackson2JsonMessageConverter());
        container.setConcurrentConsumers(2);
        container.setMaxConcurrentConsumers(20);
        // 这是手动应答方式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate(
            @Named("testConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }



}
