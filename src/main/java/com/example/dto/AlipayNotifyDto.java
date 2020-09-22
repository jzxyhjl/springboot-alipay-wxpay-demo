package com.example.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AlipayNotifyDto implements Serializable {
    /**
     * notify_time
     *
     * 通知时间
     *
     * Date
     *
     * 是
     *
     * 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss
     *
     * 2011-12-27 06:30:30
     */
    private String notify_time;
    /**
     * notify_type
     *
     * 通知类型
     *
     * String(64)
     *
     * 是
     *
     * 通知的类型
     *
     * trade_status_sync
     */
    private String notify_type;
    /**
     * notify_id
     *
     * 通知校验ID
     *
     * String(128)
     *
     * 是
     *
     * 通知校验 ID
     *
     * ac05099524730693a8b330c5ecf72da9786
     */
    private String notify_id;
    /**
     * sign_type
     *
     * 签名类型
     *
     * String(10)
     *
     * 是
     *
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2（如果开发者手动验签，不使用 SDK 验签，可以不传此参数）
     *
     * RSA2
     */
    private String sign_type;
    /**
     * sign
     *
     * 签名
     *
     * String(256)
     *
     * 是
     *
     * 请参考异步返回结果的验签（如果开发者手动验签，不使用 SDK 验签，可以不传此参数）
     *
     * 601510b7970e52cc63db0f44997cf70e
     */
    private String sign;
    /**
     * trade_no
     *
     * 支付宝交易号
     *
     * String(64)
     *
     * 是
     *
     * 支付宝交易凭证号
     *
     * 2013112011001004330000121536
     */
    private String trade_no;
    /**
     * app_id
     *
     * 开发者的app_id
     *
     * String(32)
     *
     * 是
     *
     * 支付宝分配给开发者的应用Id
     *
     * 2014072300007148
     */
    private String app_id;
    /**
     * out_trade_no
     *
     * 商户订单号
     *
     * String(64)
     *
     * 是
     *
     * 原支付请求的商户订单号
     *
     * 6823789339978248
     */
    private String out_trade_no;
    /**
     * out_biz_no
     *
     * 商户业务号
     *
     * String(64)
     *
     * 否
     *
     * 商户业务ID，主要是退款通知中返回退款申请的流水号
     *
     * HZRF001
     */
    private String out_biz_no;
    /**
     * buyer_id
     *
     * 买家支付宝用户号
     *
     * String(16)
     *
     * 否
     *
     * 买家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字
     *
     * 2088102122524333
     */
    private String buyer_id;
    /**
     * buyer_logon_id
     *
     * 买家支付宝账号
     *
     * String(100)
     *
     * 否
     *
     * 买家支付宝账号
     *
     * 15901825620
     */
    private String buyer_logon_id;
    /**
     * seller_id
     *
     * 卖家支付宝用户号
     *
     * String(30)
     *
     * 否
     *
     * 卖家支付宝用户号
     *
     * 2088101106499364
     */
    private String seller_id;
    /**
     * seller_email
     *
     * 卖家支付宝账号
     *
     * String(100)
     *
     * 否
     *
     * 卖家支付宝账号
     *
     * zhuzhanghu@alitest.com
     */
    private String seller_email;
    /**
     * trade_status
     *
     * 交易状态
     *
     * String(32)
     *
     * 是
     *
     * 交易目前所处的状态
     *
     * TRADE_CLOSED
     */
    private String trade_status;
    /**
     * total_amount
     *
     * 订单金额
     *
     * Number(9,2)
     *
     * 是
     *
     * 本次交易支付的订单金额，单位为人民币（元）
     *
     * 20
     */
    private BigDecimal total_amount;
    /**
     * receipt_amount
     *
     * 实收金额
     *
     * Number(9,2)
     *
     * 否
     *
     * 商家在交易中实际收到的款项，单位为元
     *
     * 15
     */
    private BigDecimal receipt_amount;
    /**
     * invoice_amount
     *
     * 开票金额
     *
     * Number(9,2)
     *
     * 否
     *
     * 用户在交易中支付的可开发票的金额
     *
     * 10.00
     */
    private BigDecimal invoice_amount;
    /**
     * buyer_pay_amount
     *
     * 付款金额
     *
     * Number(9,2)
     *
     * 否
     *
     * 用户在交易中支付的金额
     *
     * 13.88
     */
    private BigDecimal buyer_pay_amount;
    /**
     * point_amount
     *
     * 集分宝金额
     *
     * Number(9,2)
     *
     * 否
     *
     * 使用集分宝支付的金额
     *
     * 12.00
     */
    private BigDecimal point_amount;
    /**
     * refund_fee
     *
     * 总退款金额
     *
     * Number(9,2)
     *
     * 否
     *
     * 退款通知中，返回总退款金额，单位为元，支持两位小数
     *
     * 2.58
     */
    private BigDecimal refund_fee;
    /**
     * send_back_fee
     *
     * 实际退款金额
     *
     * Number(9,2)
     *
     * 否
     *
     * 商户实际退款给用户的金额，单位为元，支持两位小数
     *
     * 2.08
     */
    private BigDecimal send_back_fee;
    /**
     * subject
     *
     * 订单标题
     *
     * String(256)
     *
     * 否
     *
     * 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来
     *
     * 当面付交易
     */
    private String subject;
    /**
     * body
     *
     * 商品描述
     *
     * String(400)
     *
     * 否
     *
     * 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来
     *
     * 当面付交易内容
     */
    private String body;
    /**
     * gmt_create
     *
     * 交易创建时间
     *
     * Date
     *
     * 否
     *
     * 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss
     *
     * 2015-04-27 15:45:57
     */
    private String gmt_create;
    /**
     * gmt_payment
     *
     * 交易付款时间
     *
     * Date
     *
     * 否
     *
     * 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss
     *
     * 2015-04-27 15:45:57
     */
    private String gmt_payment;
    /**
     * gmt_refund
     *
     * 交易退款时间
     *
     * Date
     *
     * 否
     *
     * 该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S
     *
     * 2015-04-28 15:45:57.320
     */
    private String gmt_refund;
    /**
     * gmt_close
     *
     * 交易结束时间
     *
     * Date
     *
     * 否
     *
     * 该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss
     *
     * 2015-04-29 15:45:57
     */
    private String gmt_close;
    /**
     * fund_bill_list
     *
     * 支付金额信息
     *
     * String(512)
     *
     * 否
     *
     * 支付成功的各个渠道金额信息，详见资金明细信息说明
     *
     * [{"amount":"15.00","fundChannel":"ALIPAYACCOUNT"}]
     */
    private String fund_bill_list;
}
