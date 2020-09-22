package com.example.servcie;

import com.alipay.api.AlipayApiException;

public interface AlipayService {

    String tradePrecreate(String orderNum, Integer totalFee, String orderDesc) throws AlipayApiException;
}
