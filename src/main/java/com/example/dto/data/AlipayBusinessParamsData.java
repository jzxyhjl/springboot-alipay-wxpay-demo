package com.example.dto.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlipayBusinessParamsData implements Serializable {
    /**
     * campus_card	String	可选	64	校园卡编号	0000306634
     */
    private String campus_card;
    /**
     * card_type	String	可选	128	虚拟卡卡类型	T0HK0000
     */
    private String card_type;
    /**
     * actual_order_time	String	可选	256	实际订单时间，在乘车码场景，传入的是用户刷码乘车时间	2019-05-14 09:18:55
     */
    private String actual_order_time;
}
