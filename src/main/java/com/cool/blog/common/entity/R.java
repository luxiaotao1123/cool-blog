package com.cool.blog.common.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cool.blog.common.entity.CodeEnum.EMPTY;
import static com.cool.blog.common.entity.CodeEnum.ERROR;
import static com.cool.blog.common.entity.CodeEnum.SUCCESS;

public class R extends HashMap<String, Object> {

    private static final String CODE = "code";
    private static final String DATA = "data";

    private R() {
        this.put(CODE, SUCCESS.code);
        this.put(DATA, SUCCESS.val);
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

    public static R page(List list, Integer amount){
        R r = new R();
        r.put(DATA, new PageInfo(list, amount));
        return r;
    }

    public static R error(){
        return newR(ERROR.code, ERROR.val);
    }

    public static R error(String val){
        return newR(ERROR.code, val);
    }

    public static R empty(){
        return newR(EMPTY.code, EMPTY.val);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    static class PageInfo {

        private List list;

        private Integer amount;

        PageInfo(List list, Integer amount){
            this.list = list;
            this.amount = amount;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }

}
