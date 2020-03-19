/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsMemberServiceImpl.java
 * @Prject: com.ytz.product.service.impl
 * @Package: com.ytz.product.service.impl
 * @author: yangtianzeng
 * @date: 2020/3/16 11:09
 * @version: V1.0
 */
package com.ytz.product.service.impl;

import com.ytz.product.common.CommonResult;
import com.ytz.product.service.RedisService;
import com.ytz.product.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @ClassName: UmsMemberServiceImpl
 * @Description: 会员管理UmsMemberService实现类
 * @author: yangtianzeng
 * @date: 2020/3/16 11:09
 */
@Service
@Transactional
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    // 验证码字符集
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};



    @Override
    public CommonResult generateAuthCode(String telephone) {
        if(StringUtils.isEmpty(telephone)) {
            return CommonResult.failed("请输入手机号");
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            char num = CHARS[random.nextInt(CHARS.length)];
            sb.append(num);
        }

        //绑定手机号 并 验证码加入redis
        redisService.setData(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString(), AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if(StringUtils.isEmpty(telephone)) {
            return CommonResult.failed("请输入手机号");
        }
        if(StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode  = redisService.getData(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if(authCode.equals(realAuthCode)) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }
}
