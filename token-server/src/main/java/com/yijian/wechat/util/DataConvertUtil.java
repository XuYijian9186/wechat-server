package com.yijian.wechat.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yijian.wechat.bean.DataInstance;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class DataConvertUtil {
    public static ObjectMapper mapper = new ObjectMapper();

    public static String inputStreamToString(InputStream inputStream){
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // byte[] bys = new byte[1024];
            // int len = 0;
            String ss = null;
            if(reader != null){
                while(true){
                    ss = reader.readLine();
                    if(ss !=null)
                        builder.append(ss);
                    else
                        break;
                }
            }

        } catch (IOException e) {
            log.error("exception : "+e.getMessage());
            return "";
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("exception : "+e.getMessage());
                return "";
            }
        }
        return builder.toString().trim();
    }


    public static String xmlContentResolve(String content){
        Map<String,Object> map = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(content);
            Element rootElement = document.getRootElement();

            Iterator iterator = rootElement.elementIterator();

            while(iterator.hasNext()){
                Element child = (Element) iterator.next();
                map.put(child.getName(),child.getData());
            }

        } catch (DocumentException e) {
            log.error("exception : "+e.getMessage());
            return null;
        }

        try {
            String s = mapper.writeValueAsString(map);
            return s;
        } catch (Exception e) {
            log.error("exception : "+e.getMessage());
            return "";
        }
    }

}
