package com.cool.blog.common.entity;

public class PageHelper {

    private int pageIndex=1;

    private int pageSize=8;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int index() {
        return (getPageIndex() - 1) * getPageSize();
    }

}
