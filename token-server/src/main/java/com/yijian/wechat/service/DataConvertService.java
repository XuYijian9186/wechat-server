package com.yijian.wechat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijian.wechat.bean.DataInstance;
import com.yijian.wechat.util.DataConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class DataConvertService {

    @Autowired
    private ObjectMapper mapper;

    public DataInstance getPostParamter(ServletInputStream inputStream) {
        String s = DataConvertUtil.inputStreamToString(inputStream);
        String jsonString = DataConvertUtil.xmlContentResolve(s);
        try {
            DataInstance dataInstance = mapper.readValue(jsonString, DataInstance.class);
            return dataInstance;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String buildResponseContent(HttpServletRequest request, DataInstance instance) {
        DataInstance dataInstance = new DataInstance();
        dataInstance.setFromUserName(instance.getToUserName());
        dataInstance.setToUserName(instance.getFromUserName());
        dataInstance.setMsgType("text");
        dataInstance.setCreateTime(new Date().getTime());
        dataInstance.setContent("www.baidu.com");

        String xmlString = DataConvertUtil.ObjectToXmlString(dataInstance);
        return xmlString;
    }
}
