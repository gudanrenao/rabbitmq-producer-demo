package com.zhangwenit.rabbitmq.producer.demo.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangwenit.rabbitmq.dto.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/9/15 5:35 PM
 * @Version 1.0
 **/
@Component
public class RabbitSender {

    private final RabbitTemplate rabbitTemplate;

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() throws JsonProcessingException {
        User user = new User("zw", "a topic msg : TOPIC.NIHAO.01");

        //TOPIC
        rabbitTemplate.convertAndSend("ZW_TOPIC_DEMO_EXCHANGE", "TOPIC.NIHAO.01", user);
        rabbitTemplate.convertAndSend("ZW_TOPIC_DEMO_EXCHANGE","TOPIC.1234.02","a topic msg : TOPIC.1234.02");

        //FANOUT
        rabbitTemplate.convertAndSend("ZW_FANOUT_DEMO_EXCHANGE",null,"a fanout msg : no routing key");

        // 发送JSON字符串
        ObjectMapper mapper = new ObjectMapper();

        //DIRECT
        user.setPwd("a direct msg : DIRECT.01");
        String json = mapper.writeValueAsString(user);
        rabbitTemplate.convertAndSend("ZW_DIRECT_DEMO_EXCHANGE","DIRECT.01", json);

        user.setPwd("a direct msg : DIRECT.02");
        json = mapper.writeValueAsString(user);
        rabbitTemplate.convertAndSend("ZW_DIRECT_DEMO_EXCHANGE","DIRECT.02", json);

    }
}