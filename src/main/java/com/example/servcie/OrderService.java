package com.example.servcie;


import com.example.dto.OrderDto;

import java.util.UUID;

public interface OrderService {

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
