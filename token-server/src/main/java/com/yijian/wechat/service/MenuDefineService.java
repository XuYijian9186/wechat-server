package com.yijian.wechat.service;

import com.yijian.wechat.frame.bean.request.MenuDefineInstance;
import com.yijian.wechat.frame.bean.response.BasicResponseInstance;
import com.yijian.wechat.frame.handler.MenuDefineHandler;
import com.yijian.wechat.frame.top.WXServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MenuDefineService {

    @Autowired
    private MenuDefineHandler menuDefineHandler;

    @Value("${wechat.menu.add.url}")
    private String url;

    @Autowired
    private WechatTokenRefreshService wechatTokenRefreshService;

    public void addMenu(MenuDefineInstance instance) throws Exception {
        BasicResponseInstance execute = menuDefineHandler.execute(url, wechatTokenRefreshService.getToken(), instance);
    }
}
