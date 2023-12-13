package com.qcs;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;
    @Test
    public void testIncr(){
        Long test = stringRedisTemplate.opsForValue().increment("test");
        System.out.println(test);
    }

    @Test
    public void testRedission(){
        System.out.println(redissonClient);
    }

}
