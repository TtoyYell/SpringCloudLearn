package com.example.nacostest.controller;

import com.example.nacostest.config.NacosConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/28 15:34
 */
@RestController
@RefreshScope // 这个注解用于nacos配置热更新，放在有Value注解获取配置的类上，或者单独创建一个类用ConfigurationProperties注解指定前缀
public class NacosController {

    @Value("${pattern.dateformat}")
    private String pattern;

    @Autowired
    NacosConfig nacosConfig;

    @GetMapping("/gettwo")
    public String two() {
        return "2222";
    }

    @GetMapping("/getPattern")
    public String getPattern(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    @GetMapping("/getPattern2")
    public String getPattern2(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(nacosConfig.getDateformat()));
    }

    @GetMapping("/getHeader")
    public String getHeader(
            @RequestHeader(value = "name",required = false) String header){
        System.out.println(header);
        return header;
    }

    @GetMapping("/getAllHeader")
    public String getAllHeader(
            @RequestHeader(value = "all",required = false) String header){
        System.out.println(header);
        return header;
    }
}
