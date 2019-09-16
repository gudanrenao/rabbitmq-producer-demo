package com.zhangwenit.rabbitmq.producer.demo.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/9/15 5:32 PM
 * @Version 1.0
 **/
@Configuration
public class RabbitConfig {

    /**
     * 所有的消息发送都会转换成JSON格式发到交换机
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        //将channel设置为事务模式
//        rabbitTemplate.setChannelTransacted(true);

        //路由保证 mandatory=true + ReturnListener
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            System.out.println("未路由到queue，，回发消息：");
//        });

        //消费者确认模式
//        rabbitTemplate.containerAckMode(AcknowledgeMode.MANUAL);

        return rabbitTemplate;
    }


}