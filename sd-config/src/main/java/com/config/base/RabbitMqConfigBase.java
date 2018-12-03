package com.config.base;

import com.config.RabbitMqConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

/**
 * @author sukang
 */
public class RabbitMqConfigBase {

    private final Logger logger = LoggerFactory.getLogger(getClass());





    protected SimpleMessageListenerContainer defaultSimpleMessageListenerContainer(
            ConnectionFactory connectionFactory,
            int concurrentConsumers,
            int maxConcurrentConsumers,
            String[] queueNames,
            AcknowledgeMode acknowledgeMode){

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNames);
        container.setMessageListener(new RabbitMqConsumer());
        container.setMessageConverter(new Jackson2JsonMessageConverter());
        container.setConcurrentConsumers(concurrentConsumers);
        container.setMaxConcurrentConsumers(maxConcurrentConsumers);
        // 这是手动应答方式
        container.setAcknowledgeMode(acknowledgeMode);

        return container;
    }














    protected ConnectionFactory getConnectionFactory(
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
}
