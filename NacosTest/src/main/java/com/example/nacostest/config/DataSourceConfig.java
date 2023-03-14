package com.example.nacostest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/14 20:15
 */
@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }



    // 后台监控：web.xml ServletRegistrationBean
    // 因为SpringBoot 内置了 servlet容器，所以没有web.xml，所以就用替代方法：用 ServletRegistrationBean 注册进去
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        // 后台需要有人登录，账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();

        // 增加配置
        initParameters.put("loginUsername", "admin"); // 登录key是固定的loginUsername loginPassword
        initParameters.put("loginPassword", "123456");

        // 允许谁可以访问
        initParameters.put("allow", "");

        // 禁止谁能访问         initParameters.put("kuangshen", "192.168.11.123");

        bean.setInitParameters(initParameters); // 设置初始化参数
        return bean;
    }

    // filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        // 可以过滤哪些请求
        HashMap<String, String> initParameters = new HashMap<>();

        // exclusions 这些东西不进行统计，就是不会过滤这些请求
        initParameters.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);
        return bean;
    }

}
