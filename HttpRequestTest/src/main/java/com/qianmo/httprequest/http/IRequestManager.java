package com.qianmo.httprequest.http;

/**
 * Created by wangjitao on 2016/12/21 0021.
 * E-Mail：543441727@qq.com
 * 提供常用的http请求方法
 */
public interface IRequestManager {
    void get(String url, IRequestCallBack requestCallBack);

    void post(String url, String requestBodyJson, IRequestCallBack requestCallBack);

    void put(String url, String requestBodyJson, IRequestCallBack requestCallBack);

    void delete(String url, String requestBodyJson, IRequestCallBack requestCallBack);
}
