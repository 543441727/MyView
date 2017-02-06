package com.qianmo.httprequest.http;

/**
 * Created by wangjitao on 2016/12/21 0021.
 * E-Mail：543441727@qq.com
 * 请求工厂
 */
public class RequestFactory {

    public static IRequestManager getRequestManager() {
        return VolleyRequestManager.getInstance();
//        return OKHttpRequestManager.getInstance();
    }
}
