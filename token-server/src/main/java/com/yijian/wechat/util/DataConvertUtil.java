package com.yijian.wechat.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
public class DataConvertUtil {
    public static ObjectMapper mapper = new ObjectMapper();

    public static String inputStreamToString(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // byte[] bys = new byte[1024];
            // int len = 0;
            String ss = null;
            if (reader != null) {
                while (true) {
                    ss = reader.readLine();
                    if (ss != null)
                        builder.append(ss);
                    else
                        break;
                }
            }

        } catch (IOException e) {
            log.error("exception : " + e.getMessage());
            return "";
        } finally {
            try {
                inputStream.close();
                inputStream.close();
            } catch (IOException e) {
                log.error("exception : " + e.getMessage());
                return "";
            }
        }
        return builder.toString().trim();
    }


    public static String xmlContentResolve(String content) {
        Map<String, Object> map = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(content);
            Element rootElement = document.getRootElement();

            Iterator iterator = rootElement.elementIterator();

            while (iterator.hasNext()) {
                Element child = (Element) iterator.next();
                map.put(child.getName(), child.getData());
            }

        } catch (DocumentException e) {
            log.error("exception : " + e.getMessage());
            return null;
        }

        try {
            String s = mapper.writeValueAsString(map);
            return s;
        } catch (Exception e) {
            log.error("exception : " + e.getMessage());
            return "";
        }
    }

    public static String ObjectToXmlString(Object obj) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");

        Arrays.stream(fields).forEach(field -> {
            try {
                String name = field.getName();
                String nameForMethod = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                Object value = clazz.getMethod("get" + nameForMethod).invoke(obj);
                Element element = root.addElement(field.getName());
                element.addText(value == null ? "" : value.toString());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
                log.error("生成xml失败~");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        String s = document.asXML();
        return s;
    }

    public static void writeOutputStream(ServletOutputStream outputStream, String xml) {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        try {
            writer.write(xml, 0, xml.length());
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("写出数据错误");
        }finally {
            try {
                outputStream.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
