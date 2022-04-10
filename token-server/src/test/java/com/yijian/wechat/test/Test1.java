package com.yijian.wechat.test;

import com.yijian.wechat.frame.bean.request.BasicMessageInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Test1 {

    @Test
    public void run1() {
        BasicMessageInstance instance = new BasicMessageInstance();
        instance.setContent("我是谁");
        instance.setFromUserName("我");
        instance.setToUserName("你");

//        String s = DataConvertUtil.ObjectToXmlString(instance);
//        System.out.println(s);
    }
}
