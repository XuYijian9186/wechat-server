package com.yijian.wechat.controller;

import com.yijian.wechat.frame.bean.request.BasicMessageInstance;
import com.yijian.wechat.service.DataConvertService;
import com.yijian.wechat.service.WechatConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@Slf4j
public class WechatConnectController {

    @Autowired
    private WechatConnectService wechatConnectService;

    @Autowired
    private DataConvertService dataConvertService;

    @GetMapping("/connect")
    public String connect(@RequestParam(value = "signature", required = false) String signature,
                          @RequestParam(value = "timestamp", required = false) String timestamp,
                          @RequestParam(value = "nonce", required = false) String nonce,
                          @RequestParam(value = "echostr", required = false) String echostr) {

        log.info("paramter: [signature:{}, timestamp:{}, nonce:{}, echostr:{}]", signature, timestamp, nonce, echostr);
        log.info("开始校验参数");
        Boolean flag = wechatConnectService.verifyParamter(signature, timestamp, nonce, echostr);
        if (flag) {
            log.info("连接服务器成功！");
            return echostr;
        }
        log.error("连接服务器失败~~~");
        return null;
    }

    @PostMapping(value = "/connect", produces = MediaType.APPLICATION_XML_VALUE)
    public void connection(HttpServletRequest request, HttpServletResponse response) {

        //获取inputStream中的xml报文
        try {
            BasicMessageInstance instance = dataConvertService.getPostParamter(request.getInputStream());

            //回复消息
            String s = dataConvertService.buildResponseContent(request, instance);
            response.getWriter().println(s);
            System.out.println(s);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


}
