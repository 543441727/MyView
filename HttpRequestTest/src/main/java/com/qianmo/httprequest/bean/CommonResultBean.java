package com.qianmo.httprequest.bean;

/**
 * 服务端返回通用接收实体
 * Created by wangjitao on 15/10/30.
 */
public class CommonResultBean<T> {
    private String code;
    private T data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
