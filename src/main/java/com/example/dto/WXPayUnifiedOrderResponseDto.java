package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WXPayUnifiedOrderResponseDto implements Serializable {

    /**
     * 返回状态码	return_code	是	String(16)	SUCCESS
     * SUCCESS/FAIL
     *
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String return_code;
    /**
     * 返回信息	return_msg	是	String(128)	OK
     * 当return_code为FAIL时返回信息为错误原因 ，例如
     *
     * 签名失败
     *
     * 参数格式校验错误
     */
    private String return_msg;

    // --------------- 以下字段在return_code为SUCCESS的时候有返回 --------------
    /**
     * 公众账号ID	appid	是	String(32)	wx8888888888888888	调用接口提交的公众账号ID
     */
    private String appid;
    /**
     * 商户号	mch_id	是	String(32)	1900000109	调用接口提交的商户号
     */
    private String mch_id;
    /**
     * 设备号	device_info	否	String(32)	013467007045764	自定义参数，可以为请求支付的终端设备号等
     */
    private String device_info;
    /**
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	微信返回的随机字符串
     */
    private String nonce_str;
    /**
     * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	微信返回的签名值
     */
    private String sign;
    /**
     * 业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
     */
    private String result_code;
    /**
     * 错误代码	err_code	否	String(32)	 	当result_code为FAIL时返回错误代码，详细参见下文错误列表
     */
    private String err_code;
    /**
     * 错误代码描述	err_code_des	否	String(128)	 	当result_code为FAIL时返回错误描述，详细参见下文错误列表
     */
    private String err_code_des;

    // ---------------- 以下字段在return_code 和result_code都为SUCCESS的时候有返回 ---------------
    /**
     * 交易类型	trade_type	是	String(16)	JSAPI
     * JSAPI -JSAPI支付
     *
     * NATIVE -Native支付
     *
     * APP -APP支付
     */
    private String trade_type;
    /**
     * 预支付交易会话标识	prepay_id	是	String(64)	wx201410272009395522657a690389285100	微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    private String prepay_id;
    /**
     * 二维码链接	code_url	否	String(64)	weixin://wxpay/bizpayurl/up?pr=NwY5Mz9&groupid=00
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     *
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
     */
    private String code_url;

    private String mweb_url;
}
