package com.ytz.product.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CancelOrderReceiver
 * @Description: 用于从取消订单的消息队列（mall.order.cancel）里接收消息。
 * @author: yangtianzeng
 * @date: 2020/4/3 9:38
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
@Slf4j
public class CancelOrderReceiver {

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId) {
        log.info("receive delay message orderId:{}",orderId);
        portalOrderService.cancelOrder(orderId);
    }

}
