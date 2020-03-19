/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:EsProductDao.java
 * @Prject: com.ytz.product.dao
 * @Package: com.ytz.product.dao
 * @author: yangtianzeng
 * @date: 2020/3/16 20:58
 * @version: V1.0
 */
package com.ytz.product.dao;

import com.ytz.product.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: EsProductDao
 * @Description:  搜索系统中的商品管理自定义Dao
 * @author: yangtianzeng
 * @date: 2020/3/16 20:58
 */
@Repository
public interface EsProductDao {

    List<EsProduct> getAllEsProductList(@Param("id") Long id);

}
