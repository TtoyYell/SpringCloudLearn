package com.example.nacostest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/2 18:56
 */
@Component
@Data
@ConfigurationProperties(prefix = "pattern")
public class NacosConfig {
    private String dateformat;

}
