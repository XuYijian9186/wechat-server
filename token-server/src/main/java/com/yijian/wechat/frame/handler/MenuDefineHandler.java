package com.yijian.wechat.frame.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijian.wechat.frame.bean.request.MenuDefineInstance;
import com.yijian.wechat.frame.bean.response.BasicResponseInstance;
import com.yijian.wechat.frame.top.WXServerHandler;
import com.yijian.wechat.service.WechatTokenRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class MenuDefineHandler extends CommonHandler implements WXServerHandler {

    @Value("${wechat.menu.add.url}")
    private String url;

    @Autowired
    private WechatTokenRefreshService wechatTokenRefreshService;

    private final String TOKEN_PARAM_URL_KEY = "?access_token=";

    @Override
    public BasicResponseInstance execute(Object param) throws Exception {
//        Map<String, ?> map = mapper.convertValue(param, new TypeReference<Map<String, ?>>() {
//        });
        url += TOKEN_PARAM_URL_KEY + wechatTokenRefreshService.getToken();
        BasicResponseInstance result = execute(url, param);

        return result;
    }

    @Override
    public BasicResponseInstance execute() throws IOException {
        return null;
    }
}
