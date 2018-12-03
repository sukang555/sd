package com.config;

import com.common.source.RabbitMqManager;
import com.config.base.RabbitMqConfigBase;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author sukang
 */
@Configuration
public class RabbitMqConfigTest2 extends RabbitMqConfigBase{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private RabbitMqManager rabbitMqManager;


    @Bean(name = "test2ConnectionFactory")
    public ConnectionFactory get2ConnectFactory(){

        logger.info("test2ConnectionFactory 连接信息为{}",rabbitMqManager);

        //创建rabbitmq连接工厂
        return getConnectionFactory(rabbitMqManager.getHost(),
                Integer.parseInt(rabbitMqManager.getPort()),
                "/",
                rabbitMqManager.getUsername(),
                rabbitMqManager.getPwd(), "test2ConnectionFactory");
    }

    @Bean(name = "test2SimpleMessageListenerContainer")
    public SimpleMessageListenerContainer test2ListenerContainer(
            @Named("test2ConnectionFactory") ConnectionFactory connectionFactory) {

        String[] names = new String[]{"sukang"};

        SimpleMessageListenerContainer container = defaultSimpleMessageListenerContainer(
                connectionFactory,
                1,
                20, names,
                AcknowledgeMode.MANUAL);
        container.setMessageListener(new RabbitMqConsumer());
        return container;
    }

    @Bean("test2RabbitTemplate")
    public RabbitTemplate rabbitTemplate(
            @Named("test2ConnectionFactory") ConnectionFactory connectionFactory){
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
