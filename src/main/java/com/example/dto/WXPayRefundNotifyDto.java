package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WXPayRefundNotifyDto implements Serializable {

    /**
     * 返回状态码	return_code	是	String(16)	SUCCESS
     * SUCCESS/FAIL  此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
     */
    private String return_code;
    /**
     * 返回信息	return_msg	是	String(128)	OK
     * 当return_code为FAIL时返回信息为错误原因 ，例如签名失败
     */
    private String return_msg;

    // ---------- 以下字段在return_code为SUCCESS的时候有返回：------------
    /**
     * 公众账号ID appid 是 String(32) wx8888888888888888 微信分配的公众账号ID（企业号corpid即为此appId）
     */
    private String appid;
    /**
     * 退款的商户号  mch_id  是  String(32)  1900000109  微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 随机字符串  nonce_str  是  String(32)  5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，不长于32位。推荐随机数生成算法
     */
    private String nonce_str;
    /**
     * 加密信息  req_info  是  String(1024)  加密信息请用商户秘钥进行解密
     */
    private String req_info;
}
