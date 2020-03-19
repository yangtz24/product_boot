/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsMemberController.java
 * @Prject: com.ytz.product.controller
 * @Package: com.ytz.product.controller
 * @author: yangtianzeng
 * @date: 2020/3/16 11:04
 * @version: V1.0
 */
package com.ytz.product.controller;

import com.ytz.product.common.CommonResult;
import com.ytz.product.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UmsMemberController
 * @Description: 根据电话号码获取验证码的接口和校验验证码的接口
 *      会员登录注册管理Controller
 * @author: yangtianzeng
 * @date: 2020/3/16 11:04
 */
@Api(tags = "UmsMemberController", description = "会员登录注册管理", hidden = false)
@RestController
@RequestMapping("rest/ums")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping(value = "getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return umsMemberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping(value = "verifyAuthCode")
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return umsMemberService.verifyAuthCode(telephone,authCode);
    }

}
