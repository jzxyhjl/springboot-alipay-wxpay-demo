package com.example.utils.httpclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {
 
    @Value("${http.maxTotal:100}")
    private Integer maxTotal;
 
    @Value("${http.defaultMaxPerRoute:20}")
    private Integer defaultMaxPerRoute;
 
    @Value("${http.connectTimeout:10000}")
    private Integer connectTimeout;
 
    @Value("${http.connectionRequestTimeout:10000}")
    private Integer connectionRequestTimeout;
 
    @Value("${http.socketTimeout:10000}")
    private Integer socketTimeout;
 
    @Value("${http.staleConnectionCheckEnabled:true}")
    private boolean staleConnectionCheckEnabled;
 
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(maxTotal);
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }
 
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager){
 
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
 
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
 
        return httpClientBuilder;
    }
 
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }
 
 
    @SuppressWarnings("deprecation")
	@Bean(name = "builder")
    public RequestConfig.Builder getBuilder(){
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
    }
 
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder){
        return builder.build();
    }
 
}