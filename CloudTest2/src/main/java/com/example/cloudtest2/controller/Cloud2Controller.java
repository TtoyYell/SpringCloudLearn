package com.example.cloudtest2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/28 15:34
 */
@RestController
public class Cloud2Controller {

    @GetMapping("/gettwo")
    public String two() {
        return "2222";
    }

}
