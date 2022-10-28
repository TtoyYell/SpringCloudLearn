package com.example.cloudtest1;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CloudTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudTest1Application.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    // 在该服务里面，调用其他任何服务都时这个规则，yml文件里的配置只针对调用某一个服务用的规则
//    @Bean
//    public IRule randomRule(){
//        return new RandomRule();
//    }
}
