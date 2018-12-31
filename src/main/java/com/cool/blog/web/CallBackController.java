package com.cool.blog.web;

import com.cool.blog.common.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("callback")
public class CallBackController {

    @Autowired
    private AlipayService alipayService;

    /**
     * Alipay 同步回调
     * @return
     */
    @GetMapping("/alipay_return_url")
    public String alipay_return_url(HttpServletRequest request){
        return alipayService.alipayReturn(request);
    }

    /**
     * Alipay 异步回调
     * @return
     */
    @PostMapping("/alipay_notify_url")
    public String alipay_notify_url(HttpServletRequest request){
        return alipayService.alipayNotify(request);
    }

}
