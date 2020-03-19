/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:PmsBrandServiceImpl.java
 * @Prject: com.ytz.product.service.impl
 * @Package: com.ytz.product.service.impl
 * @author: yangtianzeng
 * @date: 2020/3/16 9:38
 * @version: V1.0
 */
package com.ytz.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.ytz.product.mbg.mapper.PmsBrandMapper;
import com.ytz.product.mbg.model.PmsBrand;
import com.ytz.product.mbg.model.PmsBrandExample;
import com.ytz.product.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PmsBrandServiceImpl
 * @Description: PmsBrandService实现类
 * @author: yangtianzeng
 * @date: 2020/3/16 9:38
 */
@Service
@Transactional
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;


    @Override
    public List<PmsBrand> listAll() {
        List<PmsBrand> brandList = pmsBrandMapper.selectByExample(new PmsBrandExample());
        return brandList;
    }

    @Override
    public int addBrand(PmsBrand pmsBrand) {
        int i = pmsBrandMapper.insertSelective(pmsBrand);
        if(i > 0) {
            return i;
        }
        return 0;
    }

    @Override
    public int updateBrand(PmsBrand pmsBrand, Long id) {
        if(id == null) {
            return 0;
        }
        pmsBrand.setId(id);
        int i = pmsBrandMapper.updateByPrimaryKey(pmsBrand);
        if(i > 0) {
            return i;
        }
        return 0;
    }

    @Override
    public int deleteBrand(Long id) {
       return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> pageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PmsBrand> pageList = pmsBrandMapper.selectByExample(new PmsBrandExample());
        return pageList;
    }

    @Override
    public PmsBrand getOne(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }
}
