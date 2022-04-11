package com.yijian.wechat.frame.top;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yijian.wechat.frame.bean.response.BasicResponseInstance;

import java.io.IOException;

public interface WXServerHandler {

    BasicResponseInstance execute(Object param) throws Exception;

    BasicResponseInstance execute() throws Exception;
}
