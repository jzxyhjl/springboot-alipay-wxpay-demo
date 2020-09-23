package com.example.servcie;

import java.util.Map;

/**
 * 微信支付服务
 */
public interface WXPayService {

    /**
     * jsapi方式统一下单
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productId
     * @param ip
     * @return 返回用于js调起微信支付用到的参数
     * @throws Exception
     */
    Map<String, String> unifiedOrderForJSAPI(String orderNum, String productName, String totalFee, String productId, String ip) throws Exception;

    /**
     * 二维码方式的统一下单
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productId
     * @param ip
     * @return 返回二维码链接
     * @throws Exception
     */
    String unifiedOrderForNative(String orderNum, String productName, String totalFee, String productId, String ip) throws Exception;

    /**
     * H5方式的统一下单
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productId
     * @param ip
     * @return 返回微信外调起微信支付的连接（用在h5页面的支付按钮）
     * @throws Exception
     */
    String unifiedOrderForH5(String orderNum, String productName, String totalFee, String productId, String ip) throws Exception;

    /**
     * 申请退款
     * @param orderNum
     * @param totalFee
     * @return
     * @throws Exception
     */
    boolean refund(String orderNum, Integer totalFee) throws Exception;
}
