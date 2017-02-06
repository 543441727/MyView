//package com.qianmo.httprequest.http;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
///**
// * Created by wangjitao on 2016/12/21 0021.
// * E-Mail：543441727@qq.com
// * 对OkHttp的简单封装
// */
//public class OKHttpRequestManager implements IRequestManager {
//    public static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
//    private OkHttpClient mOkHttpClient;
//
//    public static OKHttpRequestManager getInstance() {
//        return SingleOkhttp.instance;
//    }
//
//    private static class SingleOkhttp {
//        private static final OKHttpRequestManager instance = new OKHttpRequestManager();
//    }
//
//    public OKHttpRequestManager() {
//        mOkHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .build();
//    }
//
//    @Override
//    public void get(String url, IRequestCallBack requestCallBack) {
//        Request request = new Request.Builder().url(url).get().build();
//        addCallBack(requestCallBack, request);
//
//    }
//
//    @Override
//    public void post(String url, String requestBodyJson, IRequestCallBack requestCallBack) {
//        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        addCallBack(requestCallBack, request);
//    }
//
//    @Override
//    public void put(String url, String requestBodyJson, IRequestCallBack requestCallBack) {
//        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
//        Request request = new Request.Builder()
//                .url(url)
//                .put(body)
//                .build();
//        addCallBack(requestCallBack, request);
//    }
//
//    @Override
//    public void delete(String url, String requestBodyJson, IRequestCallBack requestCallBack) {
//        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
//        Request request = new Request.Builder()
//                .url(url)
//                .delete(body)
//                .build();
//        addCallBack(requestCallBack, request);
//    }
//
//
//    private void addCallBack(final IRequestCallBack requestCallBack, final Request request) {
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                requestCallBack.onFailure(e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String json = response.body().string();
//                    requestCallBack.onSuccess(json);
//                }
//
//            }
//        });
//    }
//}
//
