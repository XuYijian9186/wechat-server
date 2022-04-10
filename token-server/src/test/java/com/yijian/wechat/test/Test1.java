package com.yijian.wechat.test;

import com.yijian.wechat.bean.DataInstance;
import com.yijian.wechat.util.DataConvertUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Test1 {

    @Test
    public void run1() {
        DataInstance instance = new DataInstance();
        instance.setContent("我是谁");
        instance.setFromUserName("我");
        instance.setToUserName("你");

//        String s = DataConvertUtil.ObjectToXmlString(instance);
//        System.out.println(s);
    }
}
