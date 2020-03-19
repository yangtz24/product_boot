/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:UmsAdminLoginParam.java
 * @Prject: com.ytz.product.dto
 * @Package: com.ytz.product.dto
 * @author: yangtianzeng
 * @date: 2020/3/16 15:41
 * @version: V1.0
 */
package com.ytz.product.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: UmsAdminLoginParam
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/3/16 15:41
 */
public class UmsAdminLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
