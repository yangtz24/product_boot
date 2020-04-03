package com.ytz.mall.security.service;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @ClassName: DynamicSecurityService
 * @Description:  动态权限相关业务类
 * @author: yangtianzeng
 * @date: 2020/4/3 20:41
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     * @return
     */
    Map<String, ConfigAttribute> loadDataSource();
}
