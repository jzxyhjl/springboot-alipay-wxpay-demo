package com.example.utils.httpclient.service;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Component
public class HttpsUtil {

	private  class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private  class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public  String post(String url, String content)
			throws NoSuchAlgorithmException, KeyManagementException, IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());

		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setDoOutput(true);
		conn.addRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("Content-Type","application/json");
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes("utf-8"));
		// 刷新 关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			if (outStream != null) {
				return new String(outStream.toByteArray(), "utf-8");
			}
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			JSONObject obj = new JSONObject();
			JSONObject obj1 = new JSONObject();
			try {
				obj.put("platform_type", "CUNT");
				obj1.put("password", "dce3f0c9f7c73cc09b1e88355f30cc82");
				obj1.put("username", "simba");
				obj.put("request_data", obj1);
				obj.put("ret_type", "json");
				obj.put("serial_number", "20180520131400999BRAND1234567");
				obj.put("service_name", "verify.login");
				obj.put("timestamp", "2018-05-20 13:14:00");
				obj.put("token", "String");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			HttpsUtil HttpsUtil=new HttpsUtil();

			String str = HttpsUtil.post("https://testrnr.cu-sc.com/esb/json", obj.toString());
			System.out.println(str);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
