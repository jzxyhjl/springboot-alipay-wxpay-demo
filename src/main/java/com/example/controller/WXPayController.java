package com.example.controller;

import com.example.config.SimbaWXPayConfig;
import com.example.constant.BooleanEnum;
import com.example.constant.SimpleMessageEnum;
import com.example.constant.WXNotifyResponseEnum;
import com.example.controller.common.BaseResponse;
import com.example.controller.common.ResultCode;
import com.example.dto.OrderDto;
import com.example.dto.WXPayNotifyDto;
import com.example.dto.WXPayRefundNotifyDto;
import com.example.servcie.OrderService;
import com.example.servcie.WXPayService;
import com.example.utils.BeanMapUtil;
import com.example.utils.PaymentRequestUtil;
import com.example.utils.WechatURLUtil;
import com.example.utils.wechat.WXPayUtil;
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
    private SimbaWXPayConfig simbaWXPayConfig;
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
        String url = WechatURLUtil.getWechatQRCodeURL(simbaWXPayConfig.getAppID(), SCOPE_BASE, simbaWXPayConfig.getAuth_callback_url(), orderNum);
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
        String url = WechatURLUtil.getWechatQRCodeURL(simbaWXPayConfig.getAppID(), SCOPE_BASE, simbaWXPayConfig.getAuth_callback_url(), orderNum);
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

    @RequestMapping("/callback/authcode")
    public String callbackForAuthcode(Model model, HttpServletRequest request, String code, String state) throws Exception {
        OrderDto orderDto = orderService.getOrder(state);
        Map<String, String> map = wxpayService.unifiedOrderForJSAPI(orderDto.getOrderNum(), orderDto.getProductName(),
                orderDto.getTotalFee().toString(), code, PaymentRequestUtil.getRealIp(request));
        model.addAllAttributes(map);
        log.info("微信jsapi支付第一步的回调方法，之后进入第二步，统一下单：" + map);
        return "wxpayJSAPIPart2";
    }

    @RequestMapping("/callback")
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

    @RequestMapping("/callback/refund")
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
