/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:PmsBrandService.java
 * @Prject: com.ytz.product.service
 * @Package: com.ytz.product.service
 * @author: yangtianzeng
 * @date: 2020/3/16 9:33
 * @version: V1.0
 */
package com.ytz.product.service;

import com.ytz.product.mbg.model.PmsBrand;

import java.util.List;

/**
 * @ClassName: PmsBrandService
 * @Description: PmsBrandService 品牌管理业务接口
 * @author: yangtianzeng
 * @date: 2020/3/16 9:33
 */
public interface PmsBrandService {

    List<PmsBrand> listAll();

    int addBrand(PmsBrand pmsBrand);

    int updateBrand(PmsBrand pmsBrand, Long id);

    int deleteBrand(Long id);

    List<PmsBrand> pageList(int pageNum, int pageSize);

    PmsBrand getOne(Long id);

}
