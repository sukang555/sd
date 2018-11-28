package com.config;

import com.common.source.RabbitMqManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.*;
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
public class RabbitMqConfig {

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

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("sukang");
        container.setMessageListener(new RabbitMqConsumer());
        container.setMessageConverter(new Jackson2JsonMessageConverter());
        container.setConcurrentConsumers(2);
        container.setMaxConcurrentConsumers(20);
        // 这是手动应答方式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }














    private  ConnectionFactory getConnectionFactory(
            String host,Integer port,
            String virtualHost,
            String username,
            String pwd,
            String factoryName){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(pwd);

        cachingConnectionFactory.addChannelListener(new ChannelListener() {
            @Override
            public void onCreate(Channel channel, boolean transactional) {
                logger.info("{} channel create host:{},port:{},virtualHost:{}" +
                        "username:{},pwd:{}",factoryName,host,port,
                        virtualHost,username,pwd);
            }

            @Override
            public void onShutDown(ShutdownSignalException signal) {
                logger.info("{} channel shutDown host:{},port:{},virtualHost:{}" +
                                "username:{},pwd:{}",factoryName,host,port,
                        virtualHost,username,pwd);
            }
        });

        cachingConnectionFactory.addConnectionListener(new ConnectionListener() {
            @Override
            public void onCreate(Connection connection) {
                logger.info("{} connection create host:{},port:{},virtualHost:{}" +
                                "username:{},pwd:{}",factoryName,host,port,
                        virtualHost,username,pwd);
            }

            @Override
            public void onShutDown(ShutdownSignalException signal) {
                logger.info("{}connection shutDown host:{},port:{},virtualHost:{}" +
                                "username:{},pwd:{}",factoryName,host,port,
                        virtualHost,username,pwd);
            }
        });
        return cachingConnectionFactory;
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




    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate(
            @Named("testConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("amq.direct");
        return rabbitTemplate;
    }



}
