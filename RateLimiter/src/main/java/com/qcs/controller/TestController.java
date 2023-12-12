package com.qcs.controller;

import com.qcs.annotation.RateLimiter;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RateLimiter(key = "limit:test:",count = 5,time = 120)
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
