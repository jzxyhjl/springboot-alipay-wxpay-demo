package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WechatURLUtil {

    public final static String BASE_HOST = "http://www.xxx.com";
    public final static String SCOPE_BASE = "snsapi_base";
    public final static String SCOPE_USERINFO = "snsapi_userinfo";

    public static String getWechatQRCodeURL(String appid, String scope, String callback, String state) throws UnsupportedEncodingException {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + appid +
                "&redirect_uri=" + URLEncoder.encode(callback, "UTF-8")  +
                "&response_type=code" +
                "&scope=" + scope +
                "&state=" + state +
                "#wechat_redirect";
    }

    public static String getAuthAccessTokenURL (String appid, String secret, String code) throws UnsupportedEncodingException {
        return "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + appid +
                "&secret=" + secret +
                "&code=" + code +
                "&grant_type=authorization_code";
    }

    public static String getUserInfoURL (String accessToken, String openid) throws UnsupportedEncodingException {
        return "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + accessToken +
                "&openid=" + openid +
                "&lang=zh_CN";
    }
}
