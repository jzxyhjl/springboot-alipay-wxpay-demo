package com.example.utils;

import com.example.constant.PaymentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 客户端识别工具
 */
@Slf4j
public class PaymentRequestUtil {

	private final static String WECHAT_UA_LOWER = "micromessenger";
	private final static String ALIPAY_UA_LOWER = "alipayclient";

	/**
	 * 获取客户端为 微信/支付宝
	 */
	public static String getUserAgent(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		log.info("payment.agent = " + agent);
		if (!StringUtils.isEmpty(agent)) {
			agent = agent.toLowerCase();
			if (agent.indexOf(WECHAT_UA_LOWER) >= 0) {
				return PaymentTypeEnum.WECHAT.getName();
			} else if (agent.indexOf(ALIPAY_UA_LOWER) >= 0) {
				return PaymentTypeEnum.ALIPAY.getName();
			} else {
				return PaymentTypeEnum.OTHER.getName();
			}
		}
		return PaymentTypeEnum.UNKNOWN.getName();
	}

	/**
	 * 获取真实IP
	 * @param request 请求体
	 * @return 真实IP
	 */
	public static String getRealIp(HttpServletRequest request) {
		// 这个一般是Nginx反向代理设置的参数
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 处理多IP的情况（只取第一个IP）
		if (ip != null && ip.contains(",")) {
			String[] ipArray = ip.split(",");
			ip = ipArray[0];
		}
		return ip;
	}

	public static String getRequestInputStream(InputStream inStream) {
		try {
			int _buffer_size = 1024;
			if (inStream != null) {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] tempBytes = new byte[_buffer_size];
				int count = -1;
				while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
					outStream.write(tempBytes, 0, count);
				}
				tempBytes = null;
				outStream.flush();
				// 将流转换成字符串
				String result = new String(outStream.toByteArray(), "UTF-8");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}