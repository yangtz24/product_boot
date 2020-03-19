/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:RedisServiceImpl.java
 * @Prject: com.ytz.product.service.impl
 * @Package: com.ytz.product.service.impl
 * @author: yangtianzeng
 * @date: 2020/3/16 10:59
 * @version: V1.0
 */
package com.ytz.product.service.impl;

import com.ytz.product.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisServiceImpl
 * @Description: Redis操作实现类
 * @author: yangtianzeng
 * @date: 2020/3/16 10:59
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setData(String key, String value, long expireTime) {
        stringRedisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public String getData(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }

    @Override
    public void removeData(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decrement(String key, long delta) {
        return stringRedisTemplate.opsForValue().decrement(key, delta);
    }
}
