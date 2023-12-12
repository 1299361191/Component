package com.qcs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimiter {
    String key() default "limit:";
    int count() default 10;

    long time() default 60L;

    String message() default "您访问的太快了，请休息一下吧";
}
