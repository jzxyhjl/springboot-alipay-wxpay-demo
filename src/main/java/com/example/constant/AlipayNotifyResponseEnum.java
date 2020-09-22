package com.example.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlipayNotifyResponseEnum {
    SUCCESS("success"),
    FAIL("fail");

    private String result;
}
