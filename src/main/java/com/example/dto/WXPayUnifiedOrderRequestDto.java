package com.example.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * appid AppID:wx82be59064cc2d865
 * 商户号 1526143271
 */
@Data
public class WXPayUnifiedOrderRequestDto implements Serializable {

    /**
     * 公众账号ID	appid	是	String(32)	wx82be59064cc2d865	微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    private String appid;
    /**
     * 商户号	mch_id	是	String(32)	1526143271	微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 设备号	device_info	否	String(32)	013467007045764	自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    private String device_info;
    /**
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，长度要求在32位以内。推荐随机数生成算法
     */
    private String nonce_str;
    /**
     * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	通过签名算法计算得出的签名值，详见签名生成算法
     */
    private String sign;
    /**
     * 商品描述	body	是	String(128)	腾讯充值中心-QQ会员充值
     */
    private String body;
    /**
     * 商户订单号	out_trade_no	是	String(32)	20150806125346	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
     */
    private String out_trade_no;
    /**
     * 标价金额	total_fee	是	Int	88	订单总金额，单位为分，详见支付金额
     */
    private String total_fee;
    /**
     * 终端IP	spbill_create_ip	是	String(64)	123.12.12.123	支持IPV4和IPV6两种格式的IP地址。用户的客户端IP
     */
    private String spbill_create_ip;
    /**
     * 通知地址	notify_url	是	String(256)	http://www.weixin.qq.com/wxpay/pay.php	异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    private String notify_url;
    /**
     * 交易类型	trade_type	是	String(16)	JSAPI
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     * MWEB -H5支付
     */
    private String trade_type;
    /**
     * 商品ID	product_id	否	String(32)	12235413214070356458058	trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    private String product_id;
    /**
     * 用户标识	openid	否	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
     */
    private String openid;

}
