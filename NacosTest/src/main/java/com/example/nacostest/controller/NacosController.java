package com.example.nacostest.controller;

import com.example.nacostest.config.NacosConfig;
import com.example.nacostest.entity.User;
import com.example.nacostest.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    @Autowired
    UserServiceImpl userService;

    @Value("${pattern.dateformat}")
    private String pattern;

    @Autowired
    NacosConfig nacosConfig;

    @GetMapping("/gettwo")
    public String two() {
        return "2222";
    }

    // 输出配置中心的配置
    @GetMapping("/getPattern")
    public String getPattern(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    // 输出配置中心的配置
    @GetMapping("/getPattern2")
    public String getPattern2(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(nacosConfig.getDateformat()));
    }

    // 输出网关添加的头
    @GetMapping("/getHeader")
    public String getHeader(
            @RequestHeader(value = "name",required = false) String header){
        System.out.println(header);
        return header;
    }

    // 输出默认网关添加的头
    @GetMapping("/getAllHeader")
    public String getAllHeader(
            @RequestHeader(value = "all",required = false) String header){
        System.out.println(header);
        return header;
    }

    // 输出shiro测试
    @GetMapping("/login")
    public String testShiro(String name,String password){
        //1获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //2封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(name,password);
        //3调用login方法进行登录认证
        try {
            subject.login(token);
            return "登录成功";
        } catch (AuthenticationException e) {
            System.out.println("登陆失败");
            return "登录失败";
        }
    }
}
