package com.qianmo.eventbustest.Event;

/**
 * Created by wangjitao on 2016/10/17 0017.
 */
public class FirstEvent {
    public String getMsg() {
        return msg;
    }

    private String msg;

    public FirstEvent(String msg) {
        this.msg = msg;
    }

}
