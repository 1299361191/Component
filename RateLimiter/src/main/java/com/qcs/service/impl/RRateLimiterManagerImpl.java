package com.qcs.service.impl;

import com.qcs.annotation.RateLimiter;
import com.qcs.service.LimiterManager;
import org.redisson.api.RRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service("RRateLimiter")
public class RRateLimiterManagerImpl implements LimiterManager {

    public static final ConcurrentHashMap<String, RRateLimiter> RRateLimiters = new ConcurrentHashMap<>();

    @Override
    public Boolean tryAccess(RateLimiter rateLimiter) {
        //获取注解属性
        String key = rateLimiter.key();
        Integer maxCount = rateLimiter.count();
        Long time = rateLimiter.time();
        return true;
    }
}
