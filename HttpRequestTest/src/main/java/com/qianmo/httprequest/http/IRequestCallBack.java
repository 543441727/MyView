package com.qianmo.httprequest.http;

/**
 * Created by wangjitao on 2016/12/21 0021.
 * E-Mail：543441727@qq.com
 * 用于请求操作后的接口回调
 */
public interface IRequestCallBack {
    void onSuccess(String response);

    void onFailure(Throwable throwable);
}
