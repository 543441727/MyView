package com.qianmo.httprequest;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wangjitao on 2016/12/21 0021.
 * E-Mail：543441727@qq.com
 */
public class HttpRequestApp extends Application {
    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
    }
}
