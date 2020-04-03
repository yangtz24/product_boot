package com.ytz.mall.common.exception;

import com.ytz.mall.common.api.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理
 * @author: yangtianzeng
 * @date: 2020/4/3 15:02
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public CommonResult handle(ApiException e) {
        if (e.getIErrorCode() != null) {
            return CommonResult.failed(e.getIErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }
}
