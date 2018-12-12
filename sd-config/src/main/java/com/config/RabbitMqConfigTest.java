package com.config;

import com.source.RabbitMqManager;
import com.config.base.RabbitMqConfigBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author sukang
 */
@Configuration
public class RabbitMqConfigTest extends RabbitMqConfigBase{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private RabbitMqManager rabbitMqManager;


    @Bean(name = "testConnectionFactory")
    @Primary
    public ConnectionFactory getConnectFactory(){

        logger.info("testConnectionFactory 连接信息为{}",rabbitMqManager);

        //创建rabbitmq连接工厂
        return getConnectionFactory(rabbitMqManager.getHost(),
                Integer.parseInt(rabbitMqManager.getPort()),
                "/".concat(rabbitMqManager.getVirtualHost()),
                rabbitMqManager.getUsername(),
                rabbitMqManager.getPwd(), "testConnectionFactory");
    }

    @Bean(name = "testSimpleMessageListenerContainer")
    public SimpleMessageListenerContainer testListenerContainer(
            @Named("testConnectionFactory") ConnectionFactory connectionFactory) {

        String[] names = rabbitMqManager.getQuene().split(",");

        SimpleMessageListenerContainer container = defaultSimpleMessageListenerContainer(
                connectionFactory,
                2,
                20, names,
                AcknowledgeMode.MANUAL);
        container.setMessageListener(new RabbitMqConsumer());
        return container;
    }


    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate(
            @Named("testConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("amq.direct");
        return rabbitTemplate;
    }

    //交换机持久化

   /* @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("direct",true,false);
    }*/


    /**
     *队列持久化
     */
 /*   @Bean
    public Queue getQueue(){
        return new Queue("demo",true,false,false);
    }

    @Bean
    public Queue getQueue2(){
        return new Queue("sukang",true,false,false);
    }*/








}
