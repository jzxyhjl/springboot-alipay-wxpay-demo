package com.example.utils.httpclient;

import com.example.utils.httpclient.model.vo.HttpResult;
import com.google.common.collect.Maps;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class HttpClient {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }

    public InputStream doGetFile(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 装载配置信息
        httpGet.setConfig(config);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200
                && response.getEntity().isStreaming()) {
            // 返回响应体的内容
            return response.getEntity().getContent();
        }
        return null;
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, String> param) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (param != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, String> entry : param.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }
    public  HttpResult doGet(String url, Map<String, String> headers, Map<String, String> param) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (param != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, String> entry : param.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        // 声明httpPost请求
        HttpGet httpGet = new HttpGet(uriBuilder.build().toString());
        // 加入配置信息
        httpGet.setConfig(config);
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                httpGet.addHeader(e.getKey(), e.getValue());
            }
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        HttpResult httpResult = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));

        return httpResult;
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public HttpResult doPostForm(String url, Map<String, String> param) throws Exception {
        Map<String, String> headers = Maps.newHashMap();
        // 发起请求
        return doPostForm(url, headers, param);
    }

    /**
     * 指定请求头 且 带参数的post请求
     *
     * @param url
     * @param headers
     * @param param
     * @return
     * @throws Exception
     */
    public HttpResult doPostForm(String url, Map<String, String> headers, Map<String, String> param) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                httpPost.addHeader(e.getKey(), e.getValue());
            }
        }
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (param != null && param.size() > 0) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : param.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPostJson(String url) throws Exception {
        return this.doPostJson(url, "{}");
    }

    /**
     * 模拟发送HTTP请求
     * @author 白鹿
     * @param url
     */
    public HttpResult doPostJson(String url, String str) throws Exception {
        Map<String, String> headers = Maps.newHashMap();
        return doPostJson(url, headers, str);
    }

    public HttpResult doPostJson(String url, Map<String, String> headers, String body) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                httpPost.addHeader(e.getKey(), e.getValue());
            }
        }
        if (body != null) {
            httpPost.setEntity(new ByteArrayEntity(body.getBytes()));
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpResult httpResult = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));

        return httpResult;
    }
}