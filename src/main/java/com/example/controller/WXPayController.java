package com.example.controller;

import com.example.config.MyWXPayConfig;
import com.example.constant.SimpleMessageEnum;
import com.example.controller.common.BaseResponse;
import com.example.controller.common.ResultCode;
import com.example.dto.OrderDto;
import com.example.servcie.OrderService;
import com.example.servcie.WXPayService;
import com.example.utils.PaymentRequestUtil;
import com.example.utils.WechatURLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.example.utils.WechatURLUtil.SCOPE_BASE;

/**
 * 微信支付方式
 */
@Controller
@RequestMapping("/payment/wxpay")
@Slf4j
public class WXPayController {

    @Autowired
    private WXPayService wxpayService;
    @Autowired
    private MyWXPayConfig myWXPayConfig;
    @Autowired
    private OrderService orderService;

    /**
     * 微信支付的JSAPI模式，返回第一步静默获取openid的url
     * @param orderNum
     * @return
     * @throws Exception
     */
    @GetMapping("/jsapi/part1/url")
    @ResponseBody
    public BaseResponse<String> wxpayJSAPIPart1Url(String orderNum) throws Exception {
        String url = WechatURLUtil.getWechatQRCodeURL(myWXPayConfig.getAppID(), SCOPE_BASE, myWXPayConfig.getAuth_callback_url(), orderNum);
        return BaseResponse.<String>builder().data(url).build();
    }

    /**
     * 微信支付的JSAPI模式，返回第二步预支付订单
     * @param request
     * @param code
     * @param state
     * @return
     * @throws Exception
     */
    @GetMapping("/jsapi/part2/map")
    @ResponseBody
    public BaseResponse<Map<String, String>> wxpayJSAPIPart2Map(HttpServletRequest request, String code, String state) throws Exception {
        OrderDto orderDto = orderService.getOrder(state);
        Map<String, String> map = wxpayService.unifiedOrderForJSAPI(orderDto.getOrderNum(), orderDto.getProductName(),
                orderDto.getTotalFee().toString(), code, PaymentRequestUtil.getRealIp(request));
        return BaseResponse.<Map<String, String>>builder().data(map).build();
    }

    /**
     * 微信支付的JSAPI模式，第一步获取openid
     * @param model
     * @param orderNum
     * @return
     * @throws Exception
     */
    @GetMapping("/jsapi/part1")
    public String wxpayJSAPIPart1(Model model, String orderNum) throws Exception {
        String url = WechatURLUtil.getWechatQRCodeURL(myWXPayConfig.getAppID(), SCOPE_BASE, myWXPayConfig.getAuth_callback_url(), orderNum);
        model.addAttribute("url", url);
        return "wxpayJSAPIPart1";
    }

    @GetMapping("/jsapi/part2")
    public String wxpayJSAPIPart2(Model model, HttpServletRequest request, String code, String state) throws Exception {
        OrderDto orderDto = orderService.getOrder(state);
        Map<String, String> map = wxpayService.unifiedOrderForJSAPI(orderDto.getOrderNum(), orderDto.getProductName(),
                orderDto.getTotalFee().toString(), code, PaymentRequestUtil.getRealIp(request));
        model.addAllAttributes(map);
        return "wxpayJSAPIPart2";
    }

    @GetMapping("/native")
    @ResponseBody
    public BaseResponse<String> wxpayNative(HttpServletRequest request, String orderNum) throws Exception {
        OrderDto orderDto = orderService.getOrder(orderNum);
        String url = wxpayService.unifiedOrderForNative(orderDto.getOrderNum(), orderDto.getProductName(),
                orderDto.getTotalFee().toString(), orderDto.getProductNum(), PaymentRequestUtil.getRealIp(request));
        return BaseResponse.<String>builder().data(url).build();

    }

    @GetMapping("/h5")
    @ResponseBody
    public BaseResponse<String> wxpayH5(HttpServletRequest request, String orderNum) throws Exception {
        OrderDto orderDto = orderService.getOrder(orderNum);
        String url = wxpayService.unifiedOrderForH5(orderDto.getOrderNum(), orderDto.getProductName(),
                orderDto.getTotalFee().toString(), orderDto.getProductNum(), PaymentRequestUtil.getRealIp(request));
        return BaseResponse.<String>builder().data(url).build();

    }

    @GetMapping("/refund")
    @ResponseBody
    public BaseResponse wxpayRefund(String orderNum) throws Exception {
        OrderDto orderDto = orderService.getOrder(orderNum);
        boolean hasRefund = wxpayService.refund(orderDto.getOrderNum(), orderDto.getTotalFee());
        if (!hasRefund) {
            return BaseResponse.builder().code(ResultCode.FAILURE.getCode()).message(SimpleMessageEnum.OPTION_FAILURE.getMsg()).build();
        }
        return BaseResponse.builder().message(SimpleMessageEnum.OPTION_SUCCESS.getMsg()).build();
    }


}
