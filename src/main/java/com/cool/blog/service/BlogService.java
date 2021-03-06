package com.cool.blog.service;

import com.cool.blog.common.entity.PageHelper;
import com.cool.blog.common.entity.R;

public interface BlogService {

    /**
     * 分页获取博客
     */
    R getBlogPage(PageHelper pageHelper);

    /**
     * 获取博客详情
     */
    R getBlogDetail(String id);
}
