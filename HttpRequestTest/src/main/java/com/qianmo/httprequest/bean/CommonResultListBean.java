package com.qianmo.httprequest.bean;

import java.util.List;

/**
 * 服务端返回带有List数据的通用接收实体
 * Created by wangjitao on 15/12/1.
 */
public class CommonResultListBean<T> {
    private String code;
    private List<T> data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
