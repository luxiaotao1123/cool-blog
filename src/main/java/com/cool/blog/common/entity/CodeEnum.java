package com.cool.blog.common.entity;

public enum CodeEnum {

    SUCCESS(200, "请求成功"),
    ERROR(500, "服务器内部错误"),
    EMPTY(400,"暂无数据"),
    ;

    public int code;
    public String val;

    CodeEnum(int code, String val){
        this.code = code;
        this.val = val;
    }
}
