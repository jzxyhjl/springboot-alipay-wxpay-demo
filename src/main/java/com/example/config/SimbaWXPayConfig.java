package com.example.config;

import com.example.utils.wechat.IWXPayDomain;
import com.example.utils.wechat.WXPayConfig;
import com.example.utils.wechat.WXPayConstants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Data
@Component
public class SimbaWXPayConfig extends WXPayConfig {

    /**
     * 公众号ID	appid	是	String(32)	wx82be59064cc2d865	微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    @Value("${base.payment.wxpay.appid}")
    private String appid;
    /**
     * 公众号secret 用于获取openid（仅微信支付JSAPI模式使用）
     */
    @Value("${base.payment.wxpay.appsecret}")
    private String appsecret;
    /**
     * 商户号	mch_id	是	String(32)	1526143271	微信支付分配的商户号
     */
    @Value("${base.payment.wxpay.mch_id}")
    private String mch_id;
    /**
     * 商户的APIKey
     */
    @Value("${base.payment.wxpay.apikey}")
    private String apikey;
    /**
     * 通知地址	notify_url	是	String(256)	http://www.weixin.qq.com/wxpay/pay.php	异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @Value("${base.payment.wxpay.notify_url}")
    private String notify_url;
    /**
     * 退款通知地址
     */
    @Value("${base.payment.wxpay.refund_notify_url}")
    private String refund_notify_url;
    /**
     * 授权回调地址，用于获取openid
     */
    @Value("${base.payment.wxpay.auth_callback_url}")
    private String auth_callback_url;

    @Override
    public String getAppID() {
        return this.appid;
    }

    @Override
    public String getMchID() {
        return this.mch_id;
    }

    @Override
    public String getKey() {
        return this.apikey;
    }

    @Override
    public InputStream getCertStream() {
        ClassPathResource classPathResource = new ClassPathResource("/cert/wechat/apiclient_cert.p12");
        InputStream in = null;
        try {
            in = classPathResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        // 这个方法需要这样实现, 否则无法正常初始化WXPay
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }
}
