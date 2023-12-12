package com.qcs.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.qcs.annotation.RateLimiter;
import com.qcs.service.LimiterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CountLimiterManagerImpl implements LimiterManager {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static String LIMIT_LUA_PATH = "lua/limit.lua";

    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean tryAccess(RateLimiter rateLimiter) {
        //获取注解属性
        String key = rateLimiter.key();
        Integer maxCount = rateLimiter.count();
        Long time = rateLimiter.time();

        //构造key
        String ip = request.getRemoteAddr();
        String redisKey = key + ip;
        //构造lua脚本
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(LIMIT_LUA_PATH)));
        script.setResultType(Long.class);
        Long count = null;
        try {
            count = stringRedisTemplate.execute(script, CollUtil.newArrayList(redisKey),maxCount.toString(),time.toString());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("执行lua脚本失败");
        }
        if(count == null || count > maxCount){
            return false;
        }
        return true;
    }
}
