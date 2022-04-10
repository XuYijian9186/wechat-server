package com.yijian.wechat.bean;

import lombok.Data;

@Data
public class CustomResponse<T> {

    private String msg;

    private int status;

    private T data;

    public CustomResponse<T> success(){
        this.setMsg("success");
        this.setStatus(200);
        return this;
    }

    public CustomResponse<T> success(T data){
        this.setMsg("success");
        this.setStatus(200);
        this.setData(data);
        return this;
    }

    public CustomResponse<T> error(String msg){
        this.setMsg(msg);
        this.setStatus(500);
        return this;
    }

}
