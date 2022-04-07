package com.yijian.wechat.schedule;

import com.yijian.wechat.service.WechatTokenRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class RefreshTokenSchedule {

    @Autowired
    private WechatTokenRefreshService service;

    @Scheduled(fixedDelay = 5400000)
    public void refresh() throws Exception {
        String init = service.init();
    }

}
