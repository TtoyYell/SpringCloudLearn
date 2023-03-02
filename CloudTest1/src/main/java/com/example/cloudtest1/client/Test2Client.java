package com.example.cloudtest1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/2 22:02
 */
@FeignClient("test2")
public interface Test2Client {
    @GetMapping("/gettwo")
    String getTest2();
}
