package com.yijian.wechat.frame.handler;

import com.yijian.wechat.frame.bean.response.BasicResponseInstance;
import com.yijian.wechat.frame.top.WXServerHandler;
import com.yijian.wechat.service.WechatTokenRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MenuDelHandler extends CommonHandler implements WXServerHandler {

    @Value("${wechat.menu.del.url}")
    private String url;

    @Autowired
    private WechatTokenRefreshService wechatTokenRefreshService;

    private final String TOKEN_PARAM_URL_KEY = "?access_token=";


    @Override
    public BasicResponseInstance execute(Object param) throws Exception {
        return null;
    }

    @Override
    public BasicResponseInstance execute() throws Exception {
        url += TOKEN_PARAM_URL_KEY+wechatTokenRefreshService.getToken();
        BasicResponseInstance execute = execute(url);
        return execute;
    }
}
