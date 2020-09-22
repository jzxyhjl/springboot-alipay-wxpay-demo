package com.example.dto.data;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AlipaySettleDetailInfoData implements Serializable {
    /**
     * trans_in_type	String	必填	64	结算收款方的账户类型。
     *
     * cardAliasNo：结算收款方的银行卡编号;
     * userId：表示是支付宝账号对应的支付宝唯一用户号;
     * loginName：表示是支付宝登录号；
     * defaultSettle：表示结算到商户进件时设置的默认结算账号，结算主体为门店时不支持传defaultSettle；	cardAliasNo
     */
    private String trans_in_type;
    /**
     * trans_in	String	必填	64	结算收款方。当结算收款方类型是cardAliasNo时，本参数为用户在支付宝绑定的卡编号；结算收款方类型是userId时，本参数为用户的支付宝账号对应的支付宝唯一用户号，以2088开头的纯16位数字；当结算收款方类型是loginName时，本参数为用户的支付宝登录号；当结算收款方类型是defaultSettle时，本参数不能传值，保持为空。	A0001
     */
    private String trans_in;
    /**
     * summary_dimension	String	可选	64	结算汇总维度，按照这个维度汇总成批次结算，由商户指定。
     *
     * 目前需要和结算收款方账户类型为cardAliasNo配合使用	A0001
     */
    private String summary_dimension;
    /**
     * settle_entity_id	String	可选	64	结算主体标识。当结算主体类型为SecondMerchant时，为二级商户的SecondMerchantID；当结算主体类型为Store时，为门店的外标。	2088xxxxx;ST_0001
     */
    private String settle_entity_id;
    /**
     * settle_entity_type	String	可选	32	结算主体类型。
     * 二级商户:SecondMerchant;商户或者直连商户门店:Store	SecondMerchant、Store
     */
    private String settle_entity_type;
    /**
     * amount	Price	必填	9	结算的金额，单位为元。在创建订单和支付接口时必须和交易金额相同。在结算确认接口时必须等于交易金额减去已退款金额。	0.1
     */
    private BigDecimal amount;
    /**
     *
     */
}
