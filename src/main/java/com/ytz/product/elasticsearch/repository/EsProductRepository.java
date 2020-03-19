/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:EsProductRepository.java
 * @Prject: com.ytz.product.elasticsearch.document.repository
 * @Package: com.ytz.product.elasticsearch.document.repository
 * @author: yangtianzeng
 * @date: 2020/3/16 20:51
 * @version: V1.0
 */
package com.ytz.product.elasticsearch.repository;

import com.ytz.product.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: EsProductRepository
 * @Description: 商品ES操作类
 * @author: yangtianzeng
 * @date: 2020/3/16 20:51
 */
@Repository
public interface EsProductRepository extends ElasticsearchCrudRepository<EsProduct, Long> {

    /**
     * 搜索查询
     *
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
