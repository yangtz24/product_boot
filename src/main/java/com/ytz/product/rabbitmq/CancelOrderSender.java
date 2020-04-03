package com.ytz.product.rabbitmq;

import com.ytz.product.dto.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CancelOrderSender
 * @Description: 取消订单发送者
 * @author: yangtianzeng
 * @date: 2020/4/3 9:28
 */
@Component
@Slf4j
public class CancelOrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, long delyTime) {
        //给延迟队列发送、该消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置延迟时间 单位毫秒
                        message.getMessageProperties().setExpiration(String.valueOf(delyTime));
                        return message;
                    }
                });
        log.info("send delay message orderId:{}", orderId);
    }
}
