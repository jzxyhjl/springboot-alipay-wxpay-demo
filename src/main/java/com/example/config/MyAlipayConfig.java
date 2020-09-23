package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付宝的配置文件
 */
@Data
@Component
public class MyAlipayConfig {

    @Value("${base.payment.alipay.appid}")
    private String app_id;

    @Value("${base.payment.alipay.app_private_key}")
    private String app_private_key;

    @Value("${base.payment.alipay.alipay_public_key}")
    private String alipay_public_key;

    @Value("${base.payment.alipay.notify_url}")
    private String notify_url;

    private String gateway_url = "https://openapi.alipay.com/gateway.do";

    private String format = "json";

    private String charset = "UTF-8";

    private String sign_type = "RSA2";


}
