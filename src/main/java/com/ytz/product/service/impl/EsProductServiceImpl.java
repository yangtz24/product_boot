/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:EsProductServiceImpl.java
 * @Prject: com.ytz.product.service.impl
 * @Package: com.ytz.product.service.impl
 * @author: yangtianzeng
 * @date: 2020/3/16 20:55
 * @version: V1.0
 */
package com.ytz.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ytz.product.dao.EsProductDao;
import com.ytz.product.elasticsearch.document.EsProduct;
import com.ytz.product.elasticsearch.repository.EsProductRepository;
import com.ytz.product.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: EsProductServiceImpl
 * @Description: 商品搜索管理Service实现类
 * @author: yangtianzeng
 * @date: 2020/3/16 20:55
 */
@Service
@Transactional
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductDao esProductDao;

    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {
        List<EsProduct> productList = esProductDao.getAllEsProductList(null);
        Iterable<EsProduct> productIterable = esProductRepository.saveAll(productList);
        Iterator<EsProduct> iterator = productIterable.iterator();
        int result = 0;
        while(iterator.hasNext()) {
            result ++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct product = null;
        List<EsProduct> productList = esProductDao.getAllEsProductList(id);
        if(ObjectUtil.isNotEmpty(productList)) {
            EsProduct esProduct = productList.get(0);
            product = esProductRepository.save(esProduct);
        }
        return product;
    }

    @Override
    public void batchDelete(List<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            ids.stream().forEach(id -> {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            });
            esProductRepository.deleteAll(esProductList);
        }

    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        if(ObjectUtil.isEmpty(pageNum) && ObjectUtil.isEmpty(pageSize)) {
            pageNum = 1;
            pageSize = 5;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<EsProduct> products = esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
        return products;
    }
}
