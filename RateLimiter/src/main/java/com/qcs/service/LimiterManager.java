package com.qcs.service;

import com.qcs.annotation.RateLimiter;

public interface LimiterManager {

    Boolean tryAccess(RateLimiter rateLimiter);
}
