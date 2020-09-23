package com.example.servcie;

import com.alipay.api.AlipayApiException;

/**
 * 支付宝支付服务
 */
public interface AlipayService {

    /**
     * 支付宝预下单
     * @param orderNum
     * @param totalFee
     * @param orderDesc
     * @return
     * @throws AlipayApiException
     */
    String tradePrecreate(String orderNum, Integer totalFee, String orderDesc) throws AlipayApiException;
}
