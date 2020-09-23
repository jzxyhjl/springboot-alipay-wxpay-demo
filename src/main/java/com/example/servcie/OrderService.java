package com.example.servcie;


import com.example.dto.OrderDto;

import java.util.UUID;

/**
 * 订单相关服务
 */
public interface OrderService {

    /**
     * 获取订单，规则需要自定义，这里是demo的默认实现
     * @param orderNum
     * @return
     */
    default OrderDto getOrder(String orderNum)  {
        OrderDto orderDto = new OrderDto();
        String randomId = UUID.randomUUID().toString().substring(0,31);
        orderDto.setOrderNum(orderNum);
        orderDto.setProductNum(orderNum);
        orderDto.setProductName("test商品");
        orderDto.setTotalFee(1);
        return orderDto;
    }

}
