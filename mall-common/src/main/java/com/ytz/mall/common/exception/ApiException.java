package com.ytz.mall.common.exception;

import com.ytz.mall.common.api.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ApiException
 * @Description: 自定义异常 处理
 * @author: yangtianzeng
 * @date: 2020/4/3 14:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException {

    private IErrorCode iErrorCode;
}
