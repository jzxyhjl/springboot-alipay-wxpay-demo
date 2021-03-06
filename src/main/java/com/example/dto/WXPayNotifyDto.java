package com.example.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WXPayNotifyDto implements Serializable {
    /**
     * 返回状态码	return_code	是	String(16)	SUCCESS
     * SUCCESS/FAIL
     *
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String return_code;
    /**
     * 返回信息	return_msg	否	String(128)	签名失败
     * 返回信息，如非空，为错误原因
     *
     * 签名失败
     *
     * 参数格式校验错误
     */
    private String return_msg;
    /**
     * 小程序ID	appid	是	String(32)	wx8888888888888888	微信分配的小程序ID
     */
    private String appid;
    /**
     * 商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 设备号	device_info	否	String(32)	013467007045764	微信支付分配的终端设备号
     */
    private String device_info;
    /**
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位
     */
    private String nonce_str;
    /**
     *
     签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名
     */
    private String sign;
    /**
     * 签名类型	sign_type	否	String(32)	HMAC-SHA256	签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    private String sign_type;
    /**
     * 业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
     */
    private String result_code;
    /**
     * 错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
     */
    private String err_code;
    /**
     * 错误代码描述	err_code_des	否	String(128)	系统错误	错误返回的信息描述
     */
    private String err_code_des;
    /**
     * 用户标识	openid	是	String(128)	wxd930ea5d5a258f4f	用户在商户appid下的唯一标识
     */
    private String openid;
    /**
     * 是否关注公众账号	is_subscribe	是	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注
     */
    private String is_subscribe;
    /**
     * 交易类型	trade_type	是	String(16)	JSAPI	JSAPI、NATIVE、APP
     */
    private String trade_type;
    /**
     * 付款银行	bank_type	是	String(32)	CMC	银行类型，采用字符串类型的银行标识
     */
    private String bank_type;
    /**
     * 订单金额	total_fee	是	Int	100	订单总金额，单位为分
     */
    private Integer total_fee;
    /**
     * 应结订单金额	settlement_total_fee	否	Int	100	应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    private Integer settlement_total_fee;
    /**
     * 货币种类	fee_type	否	String(8)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    private String fee_type;
    /**
     * 现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额
     */
    private Integer cash_fee;
    /**
     * 现金支付货币类型	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    private String cash_fee_type;
    /**
     * 总代金券金额	coupon_fee	否	Int	10	代金券金额<=订单金额，订单金额-代金券金额=现金支付金额
     */
    private Integer coupon_fee;
    /**
     * 代金券使用数量	coupon_count	否	Int	1	代金券使用数量
     */
    private Integer coupon_count;
    /**
     * 代金券类型	coupon_type_$n	否	String	CASH
     * CASH--充值代金券
     * NO_CASH---非充值代金券
     *
     * 并且订单使用了免充值券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
     *
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
     * 下列情况可能导致订单不可以享受优惠：可能情况。
     */
    private String coupon_type_0;
    /**
     * 代金券ID	coupon_id_$n	否	String(20)	10000	代金券ID,$n为下标，从0开始编号
     * 注意：只有下单时订单使用了优惠，回调通知才会返回券信息。
     * 下列情况可能导致订单不可以享受优惠：可能情况。
     */
    private String coupon_id_0;
    /**
     * 单个代金券支付金额	coupon_fee_$n	否	Int	100	单个代金券支付金额,$n为下标，从0开始编号
     */
    private Integer coupon_fee_0;
    /**
     * 微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private String out_trade_no;
    /**
     * 商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
     */
    private String attach;
    /**
     * 支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    private String time_end;
}
