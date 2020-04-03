package com.ytz.product.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: OrderParam
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/3 9:46
 */
@Setter
@Getter
public class OrderParam {

    //收货地址id
    private Long memberReceiveAddressId;
    //优惠券id
    private Long couponId;
    //使用的积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType;
}
