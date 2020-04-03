package com.ytz.product.rabbitmq;

import com.ytz.product.dto.OrderParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: OmsPortalOrderController
 * @Description: 订单管理Controller
 * @author: yangtianzeng
 * @date: 2020/4/3 9:57
 */
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RestController
@RequestMapping("/order")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @PostMapping(value = "/generateOrder")
    public Object generateOrder(@RequestBody OrderParam orderParam) {
        return portalOrderService.generateOrder(orderParam);
    }


}
