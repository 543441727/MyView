package com.qianmo.httprequest.http;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.qianmo.httprequest.HttpRequestApp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangjitao on 2016/12/21 0021.
 * E-Mail：543441727@qq.com
 * 使用Volley的封装
 */
public class VolleyRequestManager implements IRequestManager {

    public static VolleyRequestManager getInstance() {
        return SingleVolley.mInstace;
    }

    private static class SingleVolley {
        private static final VolleyRequestManager mInstace = new VolleyRequestManager();
    }

    @Override
    public void get(String url, final IRequestCallBack requestCallBack) {
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                requestCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestCallBack.onFailure(error);
            }
        });
        HttpRequestApp.requestQueue.add(request);
    }

    @Override
    public void post(String url, String requestBodyJson, IRequestCallBack requestCallBack) {
        requestWithBody(url, requestBodyJson, requestCallBack, Request.Method.POST);
    }

    @Override
    public void put(String url, String requestBodyJson, IRequestCallBack requestCallBack) {
        requestWithBody(url, requestBodyJson, requestCallBack, Request.Method.PUT);
    }

    @Override
    public void delete(String url, String requestBodyJson, IRequestCallBack requestCallBack) {
        requestWithBody(url, requestBodyJson, requestCallBack, Request.Method.DELETE);
    }

    /**
     * 封装带请求体的方法请求
     *
     * @param url             请求的地址
     * @param requestBodyJson 请求方法体
     * @param requestCallBack 回调接口
     * @param method          请求方法
     */
    private void requestWithBody(String url, String requestBodyJson, final IRequestCallBack requestCallBack, int method) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(requestBodyJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonRequest<JSONObject> request = new JsonObjectRequest(method, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                requestCallBack.onSuccess(response != null ? response.toString() : null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestCallBack.onFailure(error);
            }
        });
        HttpRequestApp.requestQueue.add(request);
    }

}
