package com.yijian.wechat.frame.bean.request;

import lombok.Data;

/**
 * Created by yijian on 2017/11/14.
 */
@Data
public class BasicMessageInstance {

    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Event;
    private String Content;
    private Double MsgID;
    private String EventKey;
    private String Ticket;
    private String Status;

}
