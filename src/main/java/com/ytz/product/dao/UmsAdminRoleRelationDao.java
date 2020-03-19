/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsAdminRoleRelationDao.java
 * @Prject: com.ytz.product.dao
 * @Package: com.ytz.product.dao
 * @author: yangtianzeng
 * @date: 2020/3/16 15:15
 * @version: V1.0
 */
package com.ytz.product.dao;

import com.ytz.product.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UmsAdminRoleRelationDao
 * @Description: 后台用户与角色管理自定义Dao
 * @author: yangtianzeng
 * @date: 2020/3/16 15:15
 */
@Repository
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限(包括 +- 权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
