package com.example.controller.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用的请求接口返回
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> {
    private String message;
    @Builder.Default
    private int code = ResultCode.SUCCESS.code;

    private T data;

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS.code;
    }

}
