package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class OrderDto implements Serializable {

    @NotBlank(message = "订单号不能为空")
    private String orderNum;

    @NotBlank(message = "商品编号不能为空")
    private String productNum;

    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @NotNull(message = "订单总金额不能为空")
    private Integer totalFee;
}
