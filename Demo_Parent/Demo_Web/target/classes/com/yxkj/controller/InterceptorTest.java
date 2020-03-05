package com.yxkj.controller;


import com.yxkj.utils.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 拦截器测试
 *
 * */
@RestController
@RequestMapping("/")
public class InterceptorTest {
    @RequestMapping("test")
    public ServerResponse test(){
        return ServerResponse.serverResponseBySuccess();
    }
}
