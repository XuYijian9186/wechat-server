package com.yijian.wechat.controller;

import com.yijian.wechat.service.WechatTokenRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private WechatTokenRefreshService wechatTokenRefreshService;

    @GetMapping("/token")
    public String getToken(){
        String token = null;
        try {
            token = wechatTokenRefreshService.getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

}
