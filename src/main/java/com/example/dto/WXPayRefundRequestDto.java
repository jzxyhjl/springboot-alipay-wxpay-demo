package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WXPayRefundRequestDto implements Serializable {

    /**
     * 公众账号ID	appid	是	String(32)	wx8888888888888888	微信分配的公众账号ID（企业号corpid即为此appId）
     */
    private String appid;
    /**
     * 商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位
     */
    private String nonce_str;
    /**
     * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名
     */
    private String sign;
    /**
     * 签名类型	sign_type	否	String(32)	HMAC-SHA256	签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    private String sign_type;
    /**
     * 商户订单号	out_trade_no	String(32)	1217752501201407033233368018	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
     */
    private String transaction_id;
    /**
     * 微信订单号	transaction_id	二选一	String(32)	1217752501201407033233368018	微信生成的订单号，在支付通知中有返回
     */
    private String out_trade_no;
    /**
     * 商户退款单号	out_refund_no	是	String(64)	1217752501201407033233368018	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    private String out_refund_no;
    /**
     * 订单金额	total_fee	是	Int	100	订单总金额，单位为分，只能为整数
     */
    private Integer total_fee;
    /**
     * 退款金额	refund_fee	是	Int	100	退款总金额，订单总金额，单位为分，只能为整数
     */
    private Integer refund_fee;
    /**
     * 退款货币种类	refund_fee_type	否	String(8)	CNY	退款货币类型，需与支付一致，或者不填。符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    private String refund_fee_type;
    /**
     * 退款原因	refund_desc	否	String(80)	商品已售完
     * 若商户传入，会在下发给用户的退款消息中体现退款原因
     *
     * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
     */
    private String refund_desc;
    /**
     * 退款资金来源	refund_account	否	String(30)	REFUND_SOURCE_RECHARGE_FUNDS
     * 仅针对老资金流商户使用
     *
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     *
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    private String refund_account;
    /**
     * 退款结果通知url	notify_url	否	String(256)	https://weixin.qq.com/notify/
     * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     *
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效
     */
    private String notify_url;
}
