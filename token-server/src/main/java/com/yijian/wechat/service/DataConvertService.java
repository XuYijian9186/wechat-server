package com.yijian.wechat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijian.wechat.bean.DataInstance;
import com.yijian.wechat.util.DataConvertUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.compiler.hotspot.phases.OnStackReplacementPhase;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import java.io.IOException;

@Service
@Slf4j
public class DataConvertService {
    private ObjectMapper mapper = new ObjectMapper();

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
}
