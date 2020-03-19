/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:RedisService.java
 * @Prject: com.ytz.product.service
 * @Package: com.ytz.product.service
 * @author: yangtianzeng
 * @date: 2020/3/16 10:54
 * @version: V1.0
 */
package com.ytz.product.service;

/**
 * @ClassName: RedisService
 * @Description: redis服务接口
 * @author: yangtianzeng
 * @date: 2020/3/16 10:54
 */
public interface RedisService {

    /**
     * 存储数据 并 设置KEY的过期时间
     * @param key  键
     * @param value  值
     */
    void setData(String key, String value, long expireTime);

    /**
     * 获取数据 根据KEY
     * @param key
     * @return
     */
    String getData(String key);

    /**
     * 删除数据 ，根据KEY
     * @param key
     */
    void removeData(String key);

    /**
     * 自增操作
     * @param delta 自增步长 每次增长数量
     */
    Long increment(String key, long delta);

    /**
     * 自减操作
     * @param delta 自减步长 每次减的数量
     */
    Long decrement(String key, long delta);


}
