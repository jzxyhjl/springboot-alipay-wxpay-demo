package com.example.controller;

import com.example.constant.AlipayNotifyResponseEnum;
import com.example.dto.AlipayNotifyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/payment/alipay/callback")
@Slf4j
public class AlipayCallbackController {

    @GetMapping("/notify")
    @ResponseBody
    public String callbackAlipay(AlipayNotifyDto dto) {
        log.info("支付完成回调的通知：" + dto);
        // TODO 修改订单状态

        return AlipayNotifyResponseEnum.SUCCESS.getResult();
    }
}
