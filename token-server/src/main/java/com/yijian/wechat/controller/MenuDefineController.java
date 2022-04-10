package com.yijian.wechat.controller;

import com.yijian.wechat.bean.CustomResponse;
import com.yijian.wechat.frame.bean.request.MenuDefineInstance;
import com.yijian.wechat.service.MenuDefineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/button")
@Slf4j
public class MenuDefineController {

    @Autowired
    private MenuDefineService menuDefineService;

    @PostMapping("/add")
    public CustomResponse addButton(@RequestBody MenuDefineInstance instance){
        CustomResponse response = new CustomResponse();
        try {
            menuDefineService.addMenu(instance);
            return response.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return response.error(e.getMessage());
        }
    }
}
