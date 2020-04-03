package com.ytz.mall.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IgnoreUrlsConfig
 * @Description:  用于配置不需要保护的资源路径
 * @author: yangtianzeng
 * @date: 2020/4/3 20:45
 */
@Data
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();
}
