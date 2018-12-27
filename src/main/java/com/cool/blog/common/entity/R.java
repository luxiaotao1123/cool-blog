package com.cool.blog.common.entity;

import java.util.HashMap;
import java.util.Map;

import static com.cool.blog.common.entity.HttpStatusEnum.EMPTY;
import static com.cool.blog.common.entity.HttpStatusEnum.ERROR;
import static com.cool.blog.common.entity.HttpStatusEnum.SUCCESS;

public class R extends HashMap<String, Object> {

    private static final String CODE = "code";
    private static final String DATA = "data";

    public R() {
        this.put(CODE, SUCCESS.getCode());
        this.put(DATA, SUCCESS.getVal());
    }

    public static R ok(){
        return new R();
    }

    private static R newR(int code, String val) {
        R r = new R();
        r.put(CODE, code);
        r.put(DATA, val);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok(Object obj) {
        R r = new R();
        r.put(DATA, obj);
        return r;
    }

    public static R error(){
        return newR(ERROR.getCode(), ERROR.getVal());
    }

    public static R error(String val){
        return newR(ERROR.getCode(), val);
    }

    public static R empty(){
        return newR(EMPTY.getCode(), EMPTY.getVal());
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
