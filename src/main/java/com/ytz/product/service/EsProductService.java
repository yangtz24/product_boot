/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:EsProductService.java
 * @Prject: com.ytz.product.service
 * @Package: com.ytz.product.service
 * @author: yangtianzeng
 * @date: 2020/3/16 20:54
 * @version: V1.0
 */
package com.ytz.product.service;

import com.ytz.product.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName: EsProductService
 * @Description: 商品搜索管理  Service
 * @author: yangtianzeng
 * @date: 2020/3/16 20:54
 */
public interface EsProductService {

    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     */
    void batchDelete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
