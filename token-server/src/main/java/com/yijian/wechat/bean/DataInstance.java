package com.yijian.wechat.bean;

import lombok.Data;

/**
 * Created by yijian on 2017/11/14.
 */
@Data
public class DataInstance {

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
