package com.cool.blog.web;

import com.cool.blog.common.service.AlipayService;
import com.cool.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AlipayService alipayService;

    @GetMapping("/test")
    public String test(String orderId){
        return alipayService.execute(orderId);
    }
}
