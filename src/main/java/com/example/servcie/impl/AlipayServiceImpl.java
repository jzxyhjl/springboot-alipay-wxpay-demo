package com.example.servcie.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.example.config.MyAlipayConfig;
import com.example.dto.AlipayTradePrecreateRequestDto;
import com.example.servcie.AlipayService;
import com.example.utils.JSONUtil;
import com.example.utils.PaymentRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private MyAlipayConfig myAlipayConfig;

    @Override
    public String tradePrecreate(String orderNum, Integer totalFee, String productName) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(myAlipayConfig.getGateway_url(),
                myAlipayConfig.getApp_id(),
                myAlipayConfig.getApp_private_key(),
                myAlipayConfig.getFormat(),
                myAlipayConfig.getCharset(),
                myAlipayConfig.getAlipay_public_key(),
                myAlipayConfig.getSign_type());
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl(PaymentRequestUtil.BASE_HOST + myAlipayConfig.getNotify_url());
        // 构建参数
        AlipayTradePrecreateRequestDto dto = generateAlipayCommonRequestDto(orderNum, totalFee, productName);
        request.setBizContent(JSONUtil.beanToJson(dto, AlipayTradePrecreateRequestDto.class));
        log.info("支付宝预创建请求参数：" + request);

        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        log.info("支付宝预创建请求结果：" + response);
        if(response.isSuccess()){
            System.out.println("调用成功");
            return response.getQrCode();
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    private AlipayTradePrecreateRequestDto generateAlipayCommonRequestDto(String orderNum, Integer totalFee, String productName) {
        AlipayTradePrecreateRequestDto alipayTradePrecreateRequestDto = new AlipayTradePrecreateRequestDto();
        alipayTradePrecreateRequestDto.setOut_trade_no(orderNum);
        BigDecimal totalAmount = BigDecimal.ZERO;
        if (totalFee != null) {
            totalAmount = new BigDecimal(totalFee).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }
        alipayTradePrecreateRequestDto.setTotal_amount(totalAmount);
        alipayTradePrecreateRequestDto.setSubject(productName);

        return alipayTradePrecreateRequestDto;
    }
}
