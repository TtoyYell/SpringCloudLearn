package com.example.cloudtest1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/28 15:34
 */
@RestController
public class Cloud1Controller {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getone")
    public String one() {
        return "111";
    }

    @GetMapping("/getoneplus")
    public String getoneplus() {
        String one = "111";
        String two = restTemplate.getForObject("http://test2/gettwo", String.class);
        return one + two;
    }

}
