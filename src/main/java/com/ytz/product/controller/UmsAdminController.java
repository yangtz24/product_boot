/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsAdminController.java
 * @Prject: com.ytz.product.controller
 * @Package: com.ytz.product.controller
 * @author: yangtianzeng
 * @date: 2020/3/16 15:08
 * @version: V1.0
 */
package com.ytz.product.controller;

import com.ytz.mall.common.api.CommonResult;
import com.ytz.product.dto.UmsAdminLoginParam;
import com.ytz.product.mbg.model.UmsAdmin;
import com.ytz.product.mbg.model.UmsPermission;
import com.ytz.product.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UmsAdminController
 * @Description: 后台用户管理
 * @author: yangtianzeng
 * @date: 2020/3/16 15:08
 */
@Api(tags = "UmsAdminController", description = "后台用户管理", hidden = false)
@RestController
@RequestMapping("rest/admin")
public class UmsAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminController.class);

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("注册")
    @PostMapping("register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin, BindingResult result) {
        UmsAdmin admin = adminService.register(umsAdmin);
        if(admin == null) {
            CommonResult.failed("注册失败");
        }
        return CommonResult.success(admin);
    }

    @ApiOperation("登录成功后，返回token信息")
    @PostMapping("login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if(StringUtils.isEmpty(token)) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return CommonResult.success(map);
    }

    @ApiOperation("获取用户所有权限")
    @GetMapping("permission/{adminId}")
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
