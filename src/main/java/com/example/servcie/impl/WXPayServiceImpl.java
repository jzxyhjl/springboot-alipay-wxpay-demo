package com.example.servcie.impl;

import com.example.config.MyWXPayConfig;
import com.example.constant.BooleanEnum;
import com.example.dto.WXPayRefundRequestDto;
import com.example.dto.WXPayRefundResponseDto;
import com.example.dto.WXPayUnifiedOrderRequestDto;
import com.example.dto.WXPayUnifiedOrderResponseDto;
import com.example.exception.PaymentException;
import com.example.servcie.WXPayService;
import com.example.utils.BeanMapUtil;
import com.example.utils.JSONUtil;
import com.example.utils.WechatURLUtil;
import com.example.utils.httpclient.HttpClient;
import com.example.utils.wechat.WXPay;
import com.example.utils.wechat.WXPayConstants;
import com.example.utils.wechat.WXPayRequest;
import com.example.utils.wechat.WXPayUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Slf4j
public class WXPayServiceImpl implements WXPayService {

    @Autowired
    private MyWXPayConfig myWXPayConfig;
    @Autowired
    private HttpClient httpClient;

    /**
     * 微信支付H5的统一下单
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productNum
     * @param ip
     * @return
     * @throws Exception
     */
    @Override
    public String unifiedOrderForH5(String orderNum, String productName, String totalFee, String productNum, String ip) throws Exception {
        // 准备统一下单参数
        WXPayUnifiedOrderRequestDto dto = generateWXPayUnifiedOrderRequestDtoForH5(orderNum, productName, totalFee, productNum, ip);
        log.info("微信支付H5请求参数：" + dto.toString());
        // 发起统一下单请求
        WXPay wxPay = new WXPay(myWXPayConfig);
        Map<String, String> result = wxPay.unifiedOrder(BeanMapUtil.beanToMap(dto));
        log.info("微信支付H5请求结果：" + result.toString());
        WXPayUnifiedOrderResponseDto resultDto = BeanMapUtil.mapToBean(result, WXPayUnifiedOrderResponseDto.class);
        // 返回跳转链接
        if (resultDto != null && BooleanEnum.YES.getStringValue().equals(resultDto.getReturn_code())
                && BooleanEnum.YES.getStringValue().equals(resultDto.getResult_code())) {
            return resultDto.getMweb_url();
        } else {
            if (resultDto != null && StringUtils.hasText(resultDto.getErr_code())) {
                throw new PaymentException(resultDto.getErr_code_des());
            }
        }
        return null;
    }
    /**
     * 生成微信支付H5统一下单用到的请求数据
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productNum
     * @param ip
     * @return
     * @throws Exception
     */
    private WXPayUnifiedOrderRequestDto generateWXPayUnifiedOrderRequestDtoForH5(String orderNum, String productName, String totalFee, String productNum, String ip) throws Exception {
        String deviceInfo = "WEB";
        String tradeType = "MWEB";
        return generateWXPayUnifiedOrderRequestDto(deviceInfo, tradeType, orderNum, productName, totalFee, productNum, ip, null);
    }

    /**
     * 微信支付navive（二维码）的统一下单
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productNum
     * @param ip
     * @return
     * @throws Exception
     */
    @Override
    public String unifiedOrderForNative(String orderNum, String productName, String totalFee, String productNum, String ip) throws Exception {
        // 准备统一下单参数
        WXPayUnifiedOrderRequestDto dto = generateWXPayUnifiedOrderRequestDtoForNative(orderNum, productName, totalFee, productNum, ip);
        log.info("微信支付Native请求参数：" + dto.toString());
        // 发起统一下单请求
        WXPay wxPay = new WXPay(myWXPayConfig);
        Map<String, String> result = wxPay.unifiedOrder(BeanMapUtil.beanToMap(dto));
        log.info("微信支付Native请求结果：" + result.toString());
        WXPayUnifiedOrderResponseDto resultDto = BeanMapUtil.mapToBean(result, WXPayUnifiedOrderResponseDto.class);
        // 返回二维码链接
        if (resultDto != null && BooleanEnum.YES.getStringValue().equals(resultDto.getReturn_code())
                && BooleanEnum.YES.getStringValue().equals(resultDto.getResult_code())) {
            return resultDto.getCode_url();
        } else {
            if (resultDto != null && StringUtils.hasText(resultDto.getErr_code())) {
                throw new PaymentException(resultDto.getErr_code_des());
            }
        }
        return null;
    }
    /**
     * 生成微信支付navive（二维码）统一下单用到的请求数据
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productNum
     * @param ip
     * @return
     * @throws Exception
     */
    private WXPayUnifiedOrderRequestDto generateWXPayUnifiedOrderRequestDtoForNative(String orderNum, String productName, String totalFee, String productNum, String ip) throws Exception {
        String deviceInfo = "WEB";
        String tradeType = "NATIVE";
        return generateWXPayUnifiedOrderRequestDto(deviceInfo, tradeType, orderNum, productName, totalFee, productNum, ip, null);
    }

    /**
     * 微信支付JSAPI的统一下单
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param code
     * @param ip
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> unifiedOrderForJSAPI(String orderNum, String productName, String totalFee, String code, String ip) throws Exception {
        Map<String, String> jsapiMap = Maps.newHashMap();
        // 准备统一下单参数
        WXPayUnifiedOrderRequestDto dto = generateWXPayUnifiedOrderRequestDtoForJSAPI(orderNum, productName, totalFee, code, ip);
        log.info("微信支付JSAPI请求参数：" + dto.toString());
        // 发起统一下单请求
        WXPay wxPay = new WXPay(myWXPayConfig);
        Map<String, String> result = wxPay.unifiedOrder(BeanMapUtil.beanToMap(dto));
        log.info("微信支付JSAPI请求结果：" + result.toString());
        WXPayUnifiedOrderResponseDto resultDto = BeanMapUtil.mapToBean(result, WXPayUnifiedOrderResponseDto.class);
        // 返回页面用的参数
        if (resultDto != null && BooleanEnum.YES.getStringValue().equals(resultDto.getReturn_code())
                && BooleanEnum.YES.getStringValue().equals(resultDto.getResult_code())) {
            jsapiMap.put("appId", result.get("appid"));
            jsapiMap.put("timeStamp", String.valueOf(System.currentTimeMillis()/1000));
            jsapiMap.put("nonceStr", result.get("nonce_str"));
            jsapiMap.put("package", "prepay_id=" + result.get("prepay_id"));
            jsapiMap.put("signType", WXPayConstants.HMACSHA256);
            jsapiMap.put("paySign", WXPayUtil.generateSignature(jsapiMap, myWXPayConfig.getApikey(), WXPayConstants.SignType.HMACSHA256));
            log.info("微信支付JSAPI微信内H5调起支付：" + jsapiMap);
        } else {
            if (resultDto != null && StringUtils.hasText(resultDto.getErr_code())) {
                throw new PaymentException(resultDto.getErr_code_des());
            }
        }
        return jsapiMap;
    }
    /**
     * 生成微信支付JSAPI统一下单用到的请求数据
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param code
     * @param ip
     * @return
     * @throws Exception
     */
    private WXPayUnifiedOrderRequestDto generateWXPayUnifiedOrderRequestDtoForJSAPI(String orderNum, String productName, String totalFee, String code, String ip) throws Exception {
        String openId = getOpenId(code);
        String deviceInfo = "WEB";
        String tradeType = "JSAPI";
        return generateWXPayUnifiedOrderRequestDto(deviceInfo, tradeType, orderNum, productName, totalFee, null, ip, openId);
    }
    /**
     * 获取openid，用于微信支付JSAPI方式必传的字段
     * @return
     */
    private String getOpenId(String code) throws Exception {
        if (!StringUtils.isEmpty(code)) {
            String authAccessTokenURL = WechatURLUtil.getAuthAccessTokenURL(myWXPayConfig.getAppID(), myWXPayConfig.getAppsecret(), code);
            WXPayRequest wxPayRequest = new WXPayRequest(myWXPayConfig);
            String authAccessTokenResponse = httpClient.doGet(authAccessTokenURL);
            /*
                由于微信的部分返回值，有时为int有时为String，
                例如获取用户信息时返回的sex（用户的性别，值为1时是男性，值为2时是女性，值为0时是未知），在返回"未知"时返回的是数字0，返回"男性"时返回的是字符串"1"
                所以对于微信这部分的接口，使用map的方式接收并处理
             */
            Map<String, String> map = JSONUtil.jsonToMap(authAccessTokenResponse);
            if (map.get("openid") != null) {
                return map.get("openid");
            }
        }
        return null;
    }
    /**
     * 微信支付多个模式下统一下单的一些通用字段的构造
     * @param deviceInfo
     * @param tradeType
     * @param orderNum
     * @param productName
     * @param totalFee
     * @param productNum
     * @param ip
     * @param openId
     * @return
     * @throws Exception
     */
    private WXPayUnifiedOrderRequestDto generateWXPayUnifiedOrderRequestDto(String deviceInfo, String tradeType, String orderNum, String productName, String totalFee,
                                                                            String productNum, String ip, String openId) throws Exception {
        // 先补全dto
        WXPayUnifiedOrderRequestDto dto = new WXPayUnifiedOrderRequestDto();

        BeanUtils.copyProperties(myWXPayConfig, dto);
        if (StringUtils.isEmpty(dto.getAppid())) {
            dto.setAppid(myWXPayConfig.getAppID());
        }
        dto.setDevice_info(deviceInfo);
        dto.setTrade_type(tradeType);
        dto.setNonce_str(WXPayUtil.generateNonceStr());
        dto.setBody(productName);
        dto.setOut_trade_no(orderNum);
        dto.setTotal_fee(totalFee);
        dto.setSpbill_create_ip(ip);
        if (StringUtils.hasText(productNum)) {
            dto.setProduct_id(productNum);
        }
        if (StringUtils.hasText(openId)) {
            dto.setOpenid(openId);
        }

        dto.setSign(WXPayUtil.generateSignature(BeanMapUtil.beanToMap(dto), myWXPayConfig.getApikey()));
        return dto;
    }

    /**
     * 退款申请
     * @param orderNum
     * @param totalFee
     * @return
     * @throws Exception
     */
    @Override
    public boolean refund(String orderNum, Integer totalFee) throws Exception {
        // 准备退款参数
        WXPayRefundRequestDto dto = generateWXPayRefundRequestDto(orderNum, totalFee);
        log.info("申请退款参数：" + dto);
        WXPay wxPayt = new WXPay(myWXPayConfig);
        Map<String, String> result = wxPayt.refund(BeanMapUtil.beanToMap(dto));
        log.info("申请退款结果：" + result);
        WXPayRefundResponseDto resultDto = BeanMapUtil.mapToBean(result, WXPayRefundResponseDto.class);
        if (resultDto != null && BooleanEnum.YES.getStringValue().equals(resultDto.getReturn_code())
                            && BooleanEnum.YES.getStringValue().equals(resultDto.getResult_code())) {
            return BooleanEnum.YES.getBooleanValue();
        } else {
            if (resultDto != null && StringUtils.hasText(resultDto.getErr_code())) {
                throw new PaymentException(resultDto.getErr_code_des());
            }
        }
        return  BooleanEnum.NO.getBooleanValue();
    }
    /**
     * 退款申请用的请求数据
     * @param orderNum
     * @param totalFee
     * @return
     * @throws Exception
     */
    private WXPayRefundRequestDto generateWXPayRefundRequestDto(String orderNum, Integer totalFee) throws Exception {
        WXPayRefundRequestDto dto = new WXPayRefundRequestDto();
        BeanUtils.copyProperties(myWXPayConfig, dto);
        if (StringUtils.isEmpty(dto.getAppid())) {
            dto.setAppid(myWXPayConfig.getAppID());
        }
        dto.setNotify_url(myWXPayConfig.getRefund_notify_url());
        dto.setNonce_str(WXPayUtil.generateNonceStr());
        dto.setOut_trade_no(orderNum);
        dto.setOut_refund_no("refund_" + orderNum);
        dto.setTotal_fee(totalFee);
        dto.setRefund_fee(totalFee);
        dto.setSign(WXPayUtil.generateSignature(BeanMapUtil.beanToMap(dto), myWXPayConfig.getApikey()));
        return dto;
    }

}
