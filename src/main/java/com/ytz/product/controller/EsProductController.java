/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:EsProductController.java
 * @Prject: com.ytz.product.controller
 * @Package: com.ytz.product.controller
 * @author: yangtianzeng
 * @date: 2020/3/16 21:17
 * @version: V1.0
 */
package com.ytz.product.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ytz.mall.common.api.CommonPage;
import com.ytz.mall.common.api.CommonResult;
import com.ytz.product.elasticsearch.document.EsProduct;
import com.ytz.product.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EsProductController
 * @Description: 搜索商品管理Controller
 * @author: yangtianzeng
 * @date: 2020/3/16 21:17
 */
@Api(tags = "EsProductController", description = "搜索商品管理Controller")
@RestController
@RequestMapping("/rest/esProduct")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping("")
    public CommonResult<Integer> importAll() {
        int i = esProductService.importAll();
        return CommonResult.success(i);
    }

    @ApiOperation(value = "根据id删除商品")
    @PostMapping("remove/{id}")
    public CommonResult<Object> remove(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id删除商品")
    @PostMapping("remove/batch")
    public CommonResult<Object> batchRemove(@RequestParam("ids") List<Long> ids) {
        esProductService.batchDelete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @PostMapping("create/{id}")
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if(ObjectUtil.isNotEmpty(esProduct)) {
            return CommonResult.success(esProduct);
        }
        return CommonResult.failed("没有创建成功");
    }

    @ApiOperation(value = "搜索")
    @GetMapping("search")
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> productPage = esProductService.search(keyword, pageNum, pageSize);
        if(ObjectUtil.isNotEmpty(productPage)) {
            List<EsProduct> esProducts = new ArrayList<>();
            productPage.stream().forEach(product -> {
                EsProduct esProduct = new EsProduct();
                BeanUtils.copyProperties(product, esProduct);
                esProducts.add(esProduct);
            });
            return CommonResult.success(CommonPage.restPage(esProducts));
        }
        return CommonResult.failed("没有数据，搜索出错");
    }


}
