package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WXPayJSAPIDto implements Serializable {

    /**
     * 时间戳，微信支付要求到秒，10位数
     */
    private String timeStamp = String.valueOf(System.currentTimeMillis()/1000);
    /**
     * 统一下单得到的，预支付订单id
     * 注意：由于package是java关键字，所以需要改个名字，而转成
     */
    private String packageStr;
    /**
     * 当前类中所有属性的微信方式签名，大小写敏感
     */
    private String paySign;
    /**
     * 应用ID，注意签名必须大写
     */
    private String appId;
    /**
     * 签名方式，需要与统一下单时使用的签名方式一致
     * 提示：统一下单虽然文档上写的默认MD5，但实际上只有在沙箱中运行时默认MD5，生产环境默认HMAC-SHA256
     */
    private String signType;
    /**
     * 随机字符串
     */
    private String nonceStr;
}
