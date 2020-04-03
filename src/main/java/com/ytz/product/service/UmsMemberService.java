/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsMemberService.java
 * @Prject: com.ytz.product.service
 * @Package: com.ytz.product.service
 * @author: yangtianzeng
 * @date: 2020/3/16 11:07
 * @version: V1.0
 */
package com.ytz.product.service;

import com.ytz.mall.common.api.CommonResult;

/**
 * @ClassName: UmsMemberService
 * @Description:会员管理Service
 * @author: yangtianzeng
 * @date: 2020/3/16 11:07
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     * @param telephone
     * @param authCode 验证码
     * @return
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
