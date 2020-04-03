package com.ytz.mall.common.exception;

import com.ytz.mall.common.api.IErrorCode;

/**
 * @ClassName: AssertsHandler
 * @Description: 断言处理
 * @author: yangtianzeng
 * @date: 2020/4/3 14:58
 */
public class AssertsHandler {

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
