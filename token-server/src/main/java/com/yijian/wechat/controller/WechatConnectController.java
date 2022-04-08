package com.yijian.wechat.controller;

import com.yijian.wechat.service.DataConvertService;
import com.yijian.wechat.service.WechatConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

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

    @PostMapping("/connect")
    public String connection(HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();

        //获取inputStream中的xml报文
        try {
            dataConvertService.getPostParamter(request.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
        }


        return null;
    }
}
