package com.ytz.product.config;

import com.ytz.product.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @ClassName: RabbitMqConfig
 * @Description: rabbitmq  配置类
 *                  消息延迟，取消超时订单
 *                  用于解决用户下单以后，订单超时如何取消订单的问题。
 * @author: yangtianzeng
 * @date: 2020/4/2 13:36
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 订单消息实际消费队列所绑定的交换机
     * @return
     */
    @Primary
    @Bean()
    public DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列队列所绑定的交换机
     * @return
     */
    @Bean()
    public DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单实际消费队列
     * @return
     */
    @Primary
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getQueueName());
    }

    /**
     * 订单延迟队列
     * @return
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getQueueName())
                //到后期之后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                //后期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /**
     * 绑定消息队列 绑定 到交换机
     * @param exchange  交换机
     * @param queue  队列
     * @return
     */
    @Primary
    @Bean
    public Binding orderBinding(DirectExchange exchange, Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());

    }

    /**
     *  将延迟队列 绑定到 交换机
     * @param exchange
     * @param ttlQueue
     * @return
     */
    @Bean
    public Binding orderTtlBinding(DirectExchange exchange, Queue ttlQueue) {
        return BindingBuilder
                .bind(ttlQueue)
                .to(exchange)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }



}
