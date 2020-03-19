/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsAdminService.java
 * @Prject: com.ytz.product.service
 * @Package: com.ytz.product.service
 * @author: yangtianzeng
 * @date: 2020/3/16 13:53
 * @version: V1.0
 */
package com.ytz.product.service;

import com.ytz.product.mbg.model.UmsAdmin;
import com.ytz.product.mbg.model.UmsPermission;

import java.util.List;

/**
 * @ClassName: UmsAdminService
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/3/16 13:53
 */
public interface UmsAdminService {

    UmsAdmin getAdminByUsername(String username);

    List<UmsPermission> getPermissionList(Long id);

    UmsAdmin register(UmsAdmin umsAdmin);

    String login(String username, String password);
}

