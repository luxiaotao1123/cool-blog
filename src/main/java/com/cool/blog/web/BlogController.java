package com.cool.blog.web;

import com.cool.blog.common.entity.PageHelper;
import com.cool.blog.common.entity.R;
import com.cool.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public R blogPage(@RequestParam(required = false)PageHelper pageHelper){
        return blogService.getBlogPage(pageHelper);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R blogDetail(@PathVariable String id){
        return blogService.getBlogDetail(id);
    }
}
