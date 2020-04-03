package com.ytz.product.rabbitmq;

import com.ytz.product.common.CommonResult;
import com.ytz.product.dto.OrderParam;

/**
 * @ClassName: OmsPortalOrderService
 * @Description:  订单管理Service
 * @author: yangtianzeng
 * @date: 2020/4/3 9:41
 */
public interface OmsPortalOrderService {

    /**
     * 取消订单
     * @param orderId
     */
    void cancelOrder(Long orderId);


    /**
     * 根据提交信息生成订单
     * @param orderParam 订单信息
     * @return
     */
    CommonResult generateOrder(OrderParam orderParam);


}
