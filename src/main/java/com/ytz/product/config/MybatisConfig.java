/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:MybatisConfig.java
 * @Prject: com.ytz.product.config
 * @Package: com.ytz.product.config
 * @author: yangtianzeng
 * @date: 2020/3/16 8:58
 * @version: V1.0
 */
package com.ytz.product.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisConfig
 * @Description: Mybatis配置类
 * @author: yangtianzeng
 * @date: 2020/3/16 8:58
 */
@Configuration
@MapperScan("com.ytz.product.mbg.mapper")
public class MybatisConfig {
}
