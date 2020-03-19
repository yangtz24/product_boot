/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:RestfulAccessDeniedHandler.java
 * @Prject: com.ytz.product.component
 * @Package: com.ytz.product.component
 * @author: yangtianzeng
 * @date: 2020/3/16 13:54
 * @version: V1.0
 */
package com.ytz.product.component;

import cn.hutool.json.JSONUtil;
import com.ytz.product.common.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: RestfulAccessDeniedHandler
 * @Description: 当访问接口没有权限时，自定义的返回结果
 * @author: yangtianzeng
 * @date: 2020/3/16 13:54
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
