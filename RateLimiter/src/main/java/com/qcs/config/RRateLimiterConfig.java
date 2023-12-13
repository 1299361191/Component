package com.qcs.config;

import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RRateLimiterConfig {
    @Autowired
    private RedissonClient redissonClient;

    @Bean
    public RRateLimiter rateLimiter(){
        RRateLimiter limiter = redissonClient.getRateLimiter("limiter");
        //针对所有实例  每10s产生5个令牌
        limiter.trySetRate(RateType.OVERALL,5,10, RateIntervalUnit.SECONDS);
    }
}
