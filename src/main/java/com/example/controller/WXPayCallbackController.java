package com.example.controller;

import com.example.constant.BooleanEnum;
import com.example.constant.WXNotifyResponseEnum;
import com.example.dto.OrderDto;
import com.example.dto.WXPayNotifyDto;
import com.example.dto.WXPayRefundNotifyDto;
import com.example.servcie.OrderService;
import com.example.servcie.WXPayService;
import com.example.utils.BeanMapUtil;
import com.example.utils.PaymentRequestUtil;
import com.example.utils.wechat.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/payment/wxpay/callback")
@Slf4j
public class WXPayCallbackController {

    @Autowired
    private WXPayService wxpayService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/authcode")
    public String callbackForAuthcode(Model model, HttpServletRequest request, String code, String state) throws Exception {
        OrderDto orderDto = orderService.getOrder(state);
        Map<String, String> map = wxpayService.unifiedOrderForJSAPI(orderDto.getOrderNum(), orderDto.getProductName(),
                orderDto.getTotalFee().toString(), code, PaymentRequestUtil.getRealIp(request));
        model.addAllAttributes(map);
        log.info("微信jsapi支付第一步的回调方法，之后进入第二步，统一下单：" + map);
        return "wxpayJSAPIPart2";
    }

    @RequestMapping("/notify")
    @ResponseBody
    public String callbackWXPay(HttpServletRequest request) throws Exception {
        String notifyXml = PaymentRequestUtil.getRequestInputStream(request.getInputStream());
        Map<String, String> map = WXPayUtil.xmlToMap(notifyXml);
        // 处理微信返回的notify
        WXPayNotifyDto dto = BeanMapUtil.mapToBean(map, WXPayNotifyDto.class);
        System.out.println("解析后的dto -> " + dto);
        if (dto != null && BooleanEnum.YES.getStringValue().equals(dto.getReturn_code())) {
            // TODO 修改订单状态

            // TODO 通知车机端

            // 通知微信支付系统接收到信息
            return WXNotifyResponseEnum.SUCCESS.getXmlResult();
        }
        // 如果失败返回错误，微信会再次发送支付信息
        return WXNotifyResponseEnum.FAIL.getXmlResult();
    }

    @RequestMapping("/refund")
    @ResponseBody
    public String callbackRefundWXPay(WXPayRefundNotifyDto dto) {
        // 处理微信返回的notify
        System.out.println("解析后的dto -> " + dto);
        if (dto != null && BooleanEnum.YES.getStringValue().equals(dto.getReturn_code())) {
            // TODO 修改退款订单状态

            // 通知微信支付系统接收到信息
            return WXNotifyResponseEnum.SUCCESS.getXmlResult();
        }
        // 如果失败返回错误，微信会再次发送支付信息
        return WXNotifyResponseEnum.FAIL.getXmlResult();
    }
}
