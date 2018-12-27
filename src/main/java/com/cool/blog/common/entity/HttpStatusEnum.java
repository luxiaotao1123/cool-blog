package com.cool.blog.common.entity;

public enum HttpStatusEnum {

    SUCCESS(200, "success"),
    ERROR(500, "error"),
    EMPTY(400,"暂无数据"),
    ;

    private int code;
    private String val;

    HttpStatusEnum(int code, String val){
        this.code = code;
        this.val = val;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

}
