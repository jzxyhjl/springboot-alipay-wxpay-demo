package com.example.controller;

import com.example.constant.PaymentTypeEnum;
import com.example.controller.common.BaseResponse;
import com.example.utils.PaymentRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 二码合一的支付方式
 */
@Controller
@RequestMapping("/payment")
@Slf4j
public class PaymentCombinationController {

    @Autowired
    private WXPayController wxPayController;
    @Autowired
    private AlipayController alipayController;

    /**
     * 获取可以支付的二维码链接，用于生成二维码
     * @param orderNum
     * @return
     */
    @GetMapping("/qrcode/{orderNum}")
    @ResponseBody
    public BaseResponse qrcode(@PathVariable("orderNum") String orderNum) {
        String url = PaymentRequestUtil.BASE_HOST + "/backend/payment/qrcode/pay/" + orderNum;
        log.info("支付二维码链接：" + url);
        return BaseResponse.builder().data(url).build();
    }

    /**
     * 扫通用二维码，调起支付页面
     * @param orderNum
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/qrcode/pay/{orderNum}")
    public String qrcodePay(HttpServletRequest request, @PathVariable("orderNum") String orderNum, Model model) throws Exception {
        String agent = PaymentRequestUtil.getUserAgent(request);
        log.info("获取request的请求头：" + agent);
        if (PaymentTypeEnum.WECHAT.getName().equals(agent)) {
            // 如果使用微信jsapi的方式支付，通过wxpayJSAPIPart1Url获取url然后直接重定向即可
            BaseResponse<String> response = wxPayController.wxpayJSAPIPart1Url(orderNum);
            String url = response.getData();
            model.addAttribute("url", url);
            log.info("微信jsapi支付第一步，重定向获取openid：" + url);
            return "redirect:" + url;
        }
        else if (PaymentTypeEnum.ALIPAY.getName().equals(agent)) {
            BaseResponse<String> response = alipayController.precreateUrl(orderNum);
            String url = response.getData();
            model.addAttribute("url", url);
            log.info("支付宝支付：" + url);
            return "redirect:" + url;
        }
        return null;
    }
}
