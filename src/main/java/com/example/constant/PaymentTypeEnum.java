package com.example.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeEnum {

    WECHAT("wechat"),
    ALIPAY("alipay"),
    OTHER("other"),
    UNKNOWN("unknown");

    private String name;
}
