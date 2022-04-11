package com.yijian.wechat.controller;

import com.yijian.wechat.bean.CustomResponse;
import com.yijian.wechat.frame.bean.request.MenuDefineInstance;
import com.yijian.wechat.service.MenuOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/button")
@Slf4j
public class MenuOperateController {

    @Autowired
    private MenuOperateService menuDefineService;

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

    @GetMapping("/del")
    public CustomResponse delButton(){
        CustomResponse response = new CustomResponse();
        try {
            menuDefineService.delMenu();
            return response.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return response.error(e.getMessage());
        }
    }
}
