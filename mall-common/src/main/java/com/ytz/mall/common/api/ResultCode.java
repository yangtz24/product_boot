/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:ResultCode.java
 * @Prject: com.ytz.product.common
 * @Package: com.ytz.product.common
 * @author: yangtianzeng
 * @date: 2020/3/16 8:55
 * @version: V1.0
 */
package com.ytz.mall.common.api;

/**
 * @ClassName: ResultCode
 * @Description:  枚举了一些常用API操作码
 * @author: yangtianzeng
 * @date: 2020/3/16 8:55
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
