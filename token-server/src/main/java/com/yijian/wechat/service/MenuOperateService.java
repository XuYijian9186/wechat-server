package com.yijian.wechat.service;

import com.yijian.wechat.frame.bean.request.MenuDefineInstance;
import com.yijian.wechat.frame.bean.response.BasicResponseInstance;
import com.yijian.wechat.frame.handler.MenuDefineHandler;
import com.yijian.wechat.frame.handler.MenuDelHandler;
import com.yijian.wechat.frame.top.WXServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MenuOperateService {

    @Autowired
    private MenuDefineHandler menuDefineHandler;

    @Autowired
    private MenuDelHandler menuDelHandler;

    public void addMenu(MenuDefineInstance instance) throws Exception {
        BasicResponseInstance execute = menuDefineHandler.execute(instance);
    }

    public void delMenu() throws Exception {
        BasicResponseInstance execute = menuDelHandler.execute();
    }
}
