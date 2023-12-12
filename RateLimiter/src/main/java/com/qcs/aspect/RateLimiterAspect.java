package com.qcs.aspect;
import cn.hutool.core.util.ObjectUtil;
import com.qcs.annotation.RateLimiter;
import com.qcs.service.LimiterManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;


@Slf4j
@Aspect
@Component
public class RateLimiterAspect {

    @Autowired
    private LimiterManager limiterManager;

    @Before("@annotation(com.qcs.annotation.RateLimiter)")
    public void doBefore(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取注解
        RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
        Boolean result = limiterManager.tryAccess(rateLimiter);
        if(ObjectUtil.isNotNull(result) && !result){
            throw new RuntimeException(rateLimiter.message());
        }
    }
}
