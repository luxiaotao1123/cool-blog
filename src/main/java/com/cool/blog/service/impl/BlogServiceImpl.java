package com.cool.blog.service.impl;

import com.cool.blog.common.entity.HttpStatusEnum;
import com.cool.blog.common.entity.PageHelper;
import com.cool.blog.common.entity.R;
import com.cool.blog.mapper.BlogMapper;
import com.cool.blog.model.domain.Blog;
import com.cool.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public R getBlogPage(PageHelper pageHelper) {
        PageHelper page = Optional.ofNullable(pageHelper).orElse(new PageHelper());
        List<Blog> blogs = blogMapper.selectByPage(page.index(), page.getPageSize());
        if (blogs.isEmpty()){
            return R.error(HttpStatusEnum.EMPTY.getCode(), HttpStatusEnum.EMPTY.getVal());
        }
        blogs.forEach(Blog::removeContent);
        return R.ok(blogs);
    }

}
