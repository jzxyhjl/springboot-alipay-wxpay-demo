package com.example.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否枚举类
 * Author:ggsk
 * CreateDate:2020/3/24
 **/
@Getter
@AllArgsConstructor
public enum BooleanEnum {
    YES(true, 1, "SUCCESS"),
    NO(false, 0, "FAIL");

    private Boolean booleanValue;
    private int intValue;
    private String stringValue;
}
