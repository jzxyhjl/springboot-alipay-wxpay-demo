package com.example.dto;

import com.example.dto.data.AlipayBusinessParamsData;
import com.example.dto.data.AlipayExtendParamsData;
import com.example.dto.data.AlipayGoodsDetailData;
import com.example.dto.data.AlipaySettleInfoData;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付宝预支付订单的封装对象
 */
@Data
public class AlipayTradePrecreateRequestDto {
    /**
     * out_trade_no	String	必选	64	商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复	20150320010101001
     */
    private String out_trade_no;
    /**
     * seller_id	String	可选	28	卖家支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID	2088102146225135
     */
    private String seller_id;
    /**
     * total_amount	Price	必选	11	订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果同时传入了【打折金额】，【不可打折金额】，【订单总金额】三者，则必须满足如下条件：【订单总金额】=【打折金额】+【不可打折金额】	88.88
     */
    private BigDecimal total_amount;
    /**
     * discountable_amount	Price	可选	11	可打折金额. 参与优惠计算的金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果该值未传入，但传入了【订单总金额】和【不可打折金额】，则该值默认为【订单总金额】-【不可打折金额】	8.88
     */
    private BigDecimal discountable_amount;
    /**
     * subject	String	必选	256	订单标题	Iphone6 16G
     */
    private String subject;
    /**
     * goods_detail	GoodsDetail[]	可选		订单包含的商品列表信息.json格式. 其它说明详见：“商品明细说明”
     */
    private List<AlipayGoodsDetailData> goods_detail;
    /**
     * body	String	可选	128	对交易或商品的描述	Iphone6 16G
     */
    private String body;
    /**
     * product_code	String	可选	64	销售产品码。
     * 如果签约的是当面付快捷版，则传OFFLINE_PAYMENT;
     * 其它支付宝当面付产品传FACE_TO_FACE_PAYMENT；
     * 不传默认使用FACE_TO_FACE_PAYMENT；	FACE_TO_FACE_PAYMENT
     */
    private String product_code;
    /**
     * operator_id	String	可选	28	商户操作员编号	yx_001
     */
    private String operator_id;
    /**
     * store_id	String	可选	32	商户门店编号	NJ_001
     */
    private String store_id;
    /**
     * disable_pay_channels	String	可选	128	禁用渠道，用户不可用指定渠道支付
     * 当有多个渠道时用“,”分隔
     * 注，与enable_pay_channels互斥
     * 渠道列表：https://docs.open.alipay.com/common/wifww7	pcredit,moneyFund,debitCardExpress
     */
    private String disable_pay_channels;
    /**
     * enable_pay_channels	String	可选	128	可用渠道，用户只能在指定渠道范围内支付
     * 当有多个渠道时用“,”分隔
     * 注，与disable_pay_channels互斥
     * 渠道列表	pcredit,moneyFund,debitCardExpress
     */
    private String enable_pay_channels;
    /**
     * terminal_id	String	可选	32	商户机具终端编号	NJ_T_001
     */
    private String terminal_id;
    /**
     * extend_params	ExtendParams	可选		业务扩展参数
     */
    private AlipayExtendParamsData extend_params;
    /**
     * timeout_express	String	可选	6	该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。	90m
     */
    private String timeout_express;
    /**
     * settle_info	SettleInfo	可选		描述结算信息，json格式，详见结算参数说明
     */
    private AlipaySettleInfoData settle_info;
    /**
     * merchant_order_no	String	可选	32	商户原始订单号，最大长度限制32位	20161008001
     */
    private String merchant_order_no;
    /**
     * business_params	BusinessParams	可选		商户传入业务信息，具体值要和支付宝约定，应用于安全，营销等参数直传场景，格式为json格式	{"data":"123"}
     */
    private AlipayBusinessParamsData business_params;
    /**
     * qr_code_timeout_express	String	可选	6	该笔订单允许的最晚付款时间，逾期将关闭交易，从生成二维码开始计时。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。	90m
     */
    private String qr_code_timeout_express;
}
