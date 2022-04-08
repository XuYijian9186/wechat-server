package com.yijian.wechat.service;

import com.yijian.wechat.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Struct;
import java.util.Arrays;

@Service
@Slf4j
public class WechatConnectService {

    @Value("${wechat.service.token}")
    private String token;

    public Boolean verifyParamter(String signature, String timestamp, String nonce, String echostr) {
        // 将token timestamp nonce三个参数按照字典顺序排序
        String[] strArr = {token, timestamp, nonce};
        Arrays.sort(strArr);
        //将排序后的结果组装成一个字符串
        StringBuilder builder = new StringBuilder();
        Arrays.stream(strArr).forEach(item -> builder.append(item));
        String verifyStr = builder.toString();
        //对字符串进行sha1加密
        String result = null;
        try {
            result = MessageUtils.sha1(verifyStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (result != null && signature.equalsIgnoreCase(result)) {
            return true;
        } else {
            return false;
        }
    }
}
