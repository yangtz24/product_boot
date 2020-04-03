package com.ytz.product.rabbitmq;

import com.ytz.mall.common.api.CommonResult;
import com.ytz.product.dto.OrderParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: OmsPortalOrderServiceImpl
 * @Description: 订单管理Service 实现类
 * @author: yangtianzeng
 * @date: 2020/4/3 9:47
 */
@Service
@Transactional
@Slf4j
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public void cancelOrder(Long orderId) {
        //todo  执行一系类取消订单操作
        log.info("process cancelOrder orderId:{}",orderId);
    }

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        // todo 下单操作
        log.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        Long orderId = 1001L;
        sendDelayMessageCancelOrder(orderId);
        return CommonResult.success(null, "下单成功！！！");
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间
        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

}
