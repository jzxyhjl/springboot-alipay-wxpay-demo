package com.example.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SimpleMessageEnum {

    CREATE_SUCCESS("创建成功"),
    CREATE_FAILURE("创建失败"),
    UPDATE_SUCCESS("更新成功"),
    UPDATE_FAILURE("更新失败"),
    DELETE_SUCCESS("删除成功"),
    DELETE_FAILURE("删除失败"),
    SAVE_SUCCESS("保存成功"),
    SAVE_FAILURE("保存失败"),
    OPTION_SUCCESS("操作成功"),
    OPTION_FAILURE("操作失败"),

    LOGIN_SUCCESS("登录成功"),
    LOGIN_FAILURE("用户名或密码错误"),
    LOGOUT_SUCCESS("退出登录成功"),
    USER_NOT_FOUND("用户不存在");

    private String msg;
}
