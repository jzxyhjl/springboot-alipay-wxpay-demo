package com.example.controller.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * 请求结果通用枚举类型
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(HttpServletResponse.SC_OK, "请求成功"),
    FAILURE(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "请求失败");

    final int code;
    final String msg;
}
