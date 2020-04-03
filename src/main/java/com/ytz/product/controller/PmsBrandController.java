/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:PmsBrandController.java
 * @Prject: com.ytz.product.controller
 * @Package: com.ytz.product.controller
 * @author: yangtianzeng
 * @date: 2020/3/16 9:49
 * @version: V1.0
 */
package com.ytz.product.controller;

import com.ytz.mall.common.api.CommonPage;
import com.ytz.mall.common.api.CommonResult;
import com.ytz.product.mbg.model.PmsBrand;
import com.ytz.product.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: PmsBrandController
 * @Description: 品牌管理    Controller
 * @author: yangtianzeng
 * @date: 2020/3/16 9:49
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理", hidden = false)
@RestController
@RequestMapping("/rest/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    /**
     *  返回全部信息
     * @return
     */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("返回全部信息")
    @GetMapping("")
    public CommonResult<List<PmsBrand>> list() {
        return CommonResult.success(pmsBrandService.listAll());
    }

    /**
     * 添加品牌信息
     * @param pmsBrand
     * @return
     */
    @PreAuthorize("hasAuthority('pms:brand:create')")
    @ApiOperation("添加品牌信息")
    @PostMapping("")
    public CommonResult add(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int i = pmsBrandService.addBrand(pmsBrand);
        if(i == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("add success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("添加失败");
            LOGGER.debug("add failed:{}", pmsBrand);
        }
        return commonResult;
    }

    /**
     * 修改品牌信息
     * @param pmsBrand
     * @return
     */
    @PreAuthorize("hasAuthority('pms:brand:update')")
    @ApiOperation("修改品牌信息")
    @PutMapping("{id}")
    public CommonResult modify(@PathVariable Long id, @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int i = pmsBrandService.updateBrand(pmsBrand, id);
        if(i == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("modify success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("修改失败");
            LOGGER.debug("modify failed:{}", pmsBrand);
        }
        return commonResult;
    }

    /**
     * 移除品牌信息 ，根据ID
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @ApiOperation("移除品牌信息 ，根据ID")
    @DeleteMapping("/remove/{id}")
    public CommonResult remove(@PathVariable Long id) {
        int i = pmsBrandService.deleteBrand(id);
        if(i == 1) {
            LOGGER.debug("remove success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("remove success :id={}", id);
            return CommonResult.success(null);
        }
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("分页查询")
    @GetMapping("page")
    public CommonResult<CommonPage<PmsBrand>> pageList(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {
        List<PmsBrand> pageList = pmsBrandService.pageList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(pageList));
    }

    /**
     * 获取某一商品品牌详情，根据ID
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("获取某一个，根据ID")
    @GetMapping("one/{id}")
    public CommonResult<PmsBrand> getOne(@PathVariable Long id) {
        return CommonResult.success(pmsBrandService.getOne(id));
    }
}
