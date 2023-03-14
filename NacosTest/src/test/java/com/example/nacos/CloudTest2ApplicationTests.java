package com.example.nacos;

import com.example.nacostest.NacosApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = NacosApplication.class)
class CloudTest2ApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        String queueName = "hello_world";
        String message = "hello";
        rabbitTemplate.convertAndSend(queueName,message);
    }
}
