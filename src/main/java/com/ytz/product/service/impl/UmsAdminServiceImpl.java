/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsAdminServiceImpl.java
 * @Prject: com.ytz.product.service.impl
 * @Package: com.ytz.product.service.impl
 * @author: yangtianzeng
 * @date: 2020/3/16 15:14
 * @version: V1.0
 */
package com.ytz.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ytz.product.dao.UmsAdminRoleRelationDao;
import com.ytz.product.mbg.mapper.UmsAdminMapper;
import com.ytz.product.mbg.model.UmsAdmin;
import com.ytz.product.mbg.model.UmsAdminExample;
import com.ytz.product.mbg.model.UmsPermission;
import com.ytz.product.service.UmsAdminService;
import com.ytz.product.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: UmsAdminServiceImpl
 * @Description: UmsAdminService实现类
 * @author: yangtianzeng
 * @date: 2020/3/16 15:14
 */
@Service
@Transactional
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;


    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
        if(ObjectUtil.isNotEmpty(umsAdmins)) {
            return umsAdmins.get(0);
        }
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long id) {
        List<UmsPermission> permissionList = adminRoleRelationDao.getPermissionList(id);
        if(ObjectUtil.isNotEmpty(permissionList)) {
            return permissionList;
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        UmsAdmin admin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdmin, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(UmsAdmin.STATUS_ENABLE);
        //判断是否有重名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //密码加密
        String encodePassword  = passwordEncoder.encode(umsAdmin.getPassword());
        admin.setPassword(encodePassword);
        int i = adminMapper.insert(admin);
        if(i > 0) {
            return admin;
        }
        return null;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码输入不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                                                                        null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}
