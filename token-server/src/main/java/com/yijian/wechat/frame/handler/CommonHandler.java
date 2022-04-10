package com.yijian.wechat.frame.handler;

import com.yijian.wechat.frame.bean.response.BasicResponseInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CommonHandler {

    @Autowired
    private RestTemplate restTemplate;

    public BasicResponseInstance execute(String url, Object param){
        BasicResponseInstance forObject = restTemplate.postForObject(url, param, BasicResponseInstance.class);
        return forObject;
    }

    public BasicResponseInstance execute(String url){
        BasicResponseInstance forObject = restTemplate.getForObject(url, BasicResponseInstance.class);
        return forObject;
    }

}
