/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:AdminUserDetails.java
 * @Prject: com.ytz.product.dto
 * @Package: com.ytz.product.dto
 * @author: yangtianzeng
 * @date: 2020/3/16 15:01
 * @version: V1.0
 */
package com.ytz.product.dto;

import com.ytz.mall.mbg.model.UmsAdmin;
import com.ytz.mall.mbg.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: AdminUserDetails
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/3/16 15:01
 */
public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;
    private List<UmsPermission> permissionList;
    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(permissionList == null) {
            return null;
        }
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(UmsAdmin.STATUS_ENABLE);
    }
}
