/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:IErrorCode.java
 * @Prject: com.ytz.product.common
 * @Package: com.ytz.product.common
 * @author: yangtianzeng
 * @date: 2020/3/16 8:56
 * @version: V1.0
 */
package com.ytz.mall.common.api;

/**
 * @ClassName: IErrorCode
 * @Description:  封装API 的错误码
 * @author: yangtianzeng
 * @date: 2020/3/16 8:56
 */
public interface IErrorCode {

    long getCode();

    String getMessage();
}
