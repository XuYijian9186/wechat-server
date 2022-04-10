package com.yijian.wechat.frame.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijian.wechat.frame.bean.request.MenuDefineInstance;
import com.yijian.wechat.frame.bean.response.BasicResponseInstance;
import com.yijian.wechat.frame.top.WXServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class MenuDefineHandler extends CommonHandler implements WXServerHandler {

    @Autowired
    private ObjectMapper mapper;

    private final String TOKEN_PARAM_URL_KEY = "?access_token=";

    @Override
    public BasicResponseInstance execute(String url, String token, Object param) throws IOException {
//        Map<String, ?> map = mapper.convertValue(param, new TypeReference<Map<String, ?>>() {
//        });
        url += TOKEN_PARAM_URL_KEY + token;
        BasicResponseInstance result = execute(url, param);

        return result;
    }
}
