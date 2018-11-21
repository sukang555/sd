package com.config;

import com.common.util.BeanUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;


/**
 * @author sukang on 2018/11/21.
 */
public class RabbitMqConsumer implements ChannelAwareMessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        logger.info("获取到的mq消息为：{}", BeanUtil.fromObjectToStr(message));

        String body = new String(message.getBody(), "utf-8");

        logger.info("获取到的mq消息body为：{}",body);

        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        //应答
        channel.basicAck(deliveryTag,false);
    }
}
