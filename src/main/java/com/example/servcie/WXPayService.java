package com.example.servcie;

import java.util.Map;

public interface WXPayService {

    Map<String, String> unifiedOrderForJSAPI(String orderNum, String productName, String totalFee, String productId, String ip) throws Exception;

    String unifiedOrderForNative(String orderNum, String productName, String totalFee, String productId, String ip) throws Exception;

    String unifiedOrderForH5(String orderNum, String productName, String totalFee, String productId, String ip) throws Exception;

    boolean refund(String orderNum, Integer totalFee) throws Exception;
}
