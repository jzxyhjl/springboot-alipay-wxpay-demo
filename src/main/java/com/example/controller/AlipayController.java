package com.example.controller;

import com.example.controller.common.BaseResponse;
import com.example.dto.OrderDto;
import com.example.servcie.AlipayService;
import com.example.servcie.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 支付宝支付方式
 */
@Controller
@RequestMapping("/payment/alipay")
@Slf4j
public class AlipayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AlipayService alipayService;

    @GetMapping("/precreate/url")
    @ResponseBody
    public BaseResponse<String> precreateUrl(String orderNum) throws Exception {
        OrderDto orderDto = orderService.getOrder(orderNum);
        String url = alipayService.tradePrecreate(orderDto.getOrderNum(), orderDto.getTotalFee(), orderDto.getProductName());
        return BaseResponse.<String>builder().data(url).build();
    }

    @GetMapping("/precreate")
    public String precreate(Model model, String orderNum) throws Exception {
        OrderDto orderDto = orderService.getOrder(orderNum);
        String url = alipayService.tradePrecreate(orderDto.getOrderNum(), orderDto.getTotalFee(), orderDto.getProductName());
        model.addAttribute("url", url);
        return "alipayPrecreate";
    }


}
