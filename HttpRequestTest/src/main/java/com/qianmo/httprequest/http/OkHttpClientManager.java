package com.qianmo.httprequest.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjitao on 16/10/15.
 */
public class OkHttpClientManager {
    private static final String TAG = "com.qianmo.httprequest.http.OkHttpClientManager";

    private static OkHttpClientManager mInstance;
    //默认的请求回调类
    private final ResultCallback<String> DEFAULT_RESULT_CALLBACK = new ResultCallback<String>(){
        @Override
        public void onError(Request request, Exception e) {}

        @Override
        public void onResponse(String response) {}
    };
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Gson mGson;
    private GetDelegate mGetDelegate = new GetDelegate();
    private PostDelegate mPostDelegate = new PostDelegate();
    private DownloadDelegate mDownloadDelegate = new DownloadDelegate();

    private OkHttpClientManager() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    public static OkHttpClientManager getInstance() {
        if (mInstance == null){
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    public static DownloadDelegate getDownloadDelegate() {
        return getInstance()._getDownloadDelegate();
    }

    /**
     * 外部可调用获取OkHttpClient对象的方法
     */
    public static OkHttpClient getClinet() {
        return getInstance().client();
    }

    /**
     * 外部可调用的取消tag的方法
     * @param tag
     */
    public static void cancelTag(Object tag) {
        getInstance()._cancelTag(tag);
    }

    /**
     * 外部可调用的Get异步请求方法
     * @param url 请求url
     * @param callback 请求完成后回调类
     */
    public static void getAsyn(String url, ResultCallback callback) {
        getInstance().getGetDelegate().getAsyn(url, callback, null);
    }

    /**
     * 外部可调用的Get异步请求方法
     * @param url 请求url
     * @param callback 请求完成后回调类
     * @param tag
     */
    public static void getAsyn(String url, ResultCallback callback, Object tag) {
        getInstance().getGetDelegate().getAsyn(url, callback, tag);
    }

    /**
     * 外部可调用的Post异步请求方法
     * @param url 请求url
     * @param params
     * @param callback 请求完成后回调类
     */
    public static void postAsyn(String url, Param[] params, final ResultCallback callback) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, null);
    }

    /**
     * 外部可调用的Post异步请求方法
     * @param url 请求url
     * @param params
     * @param callback 请求完成后回调类
     */
    public static void postAsyn(String url, Map<String, String> params, final ResultCallback callback) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, null);
    }

    /**
     * 外部可调用的Post异步请求方法
     * @param url 请求url
     * @param bodyStr
     * @param callback 请求完成后回调类
     */
    public static void postAsyn(String url, String bodyStr, final ResultCallback callback) {
        getInstance().getPostDelegate().postAsyn(url, bodyStr, callback, null);
    }

    /**
     * 外部可调用的Get异步请求方法
     * @param url 请求url
     * @param params 数组类型请求参数
     * @param callback 请求完成后回调类
     * @param tag
     */
    public static void postAsyn(String url, Param[] params, final ResultCallback callback, Object tag) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, tag);
    }

    /**
     * 外部可调用的Get异步请求方法
     * @param url 请求url
     * @param params Map类型请求参数
     * @param callback 请求完成后回调类
     * @param tag
     */
    public static void postAsyn(String url, Map<String, String> params, final ResultCallback callback, Object tag) {
        getInstance().getPostDelegate().postAsyn(url, params, callback, tag);
    }

    /**
     * 外部可调用的Get异步请求方法
     * @param url 请求url
     * @param bodyStr Map类型请求参数
     * @param callback 请求完成后回调类
     * @param tag
     */
    public static void postAsyn(String url, String bodyStr, final ResultCallback callback, Object tag) {
        getInstance().getPostDelegate().postAsyn(url, bodyStr, callback, tag);
    }

    private GetDelegate getGetDelegate(){
        return mGetDelegate;
    }

    private PostDelegate getPostDelegate(){
        return mPostDelegate;
    }

    private DownloadDelegate _getDownloadDelegate() {
        return mDownloadDelegate;
    }

    /**
     * 封装的获取OkHttpClient的方法
     */
    public OkHttpClient client() {
        return mOkHttpClient;
    }

    /**
     * 封装的取消tag的方法
     */
    private void _cancelTag(Object tag) {
        mOkHttpClient.cancel(tag);
    }

    /******************************以下为公用的工具方法******************************************/
    private String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("=");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }
    /**
     *
     * Map类型请求参数转换为数组请求参数的工具方法
     * @param params Map类型请求参数
     * @return Param[] 请求参数数组
     */
    private Param[] map2Params(Map<String, String> params) {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    /**
     * 请求回调处理方法并传递返回值
     * @param callback Map类型请求参数
     * @param request Request请求
     */
    private void deliveryResult(ResultCallback callback, Request request) {
        if (callback == null)
            callback = DEFAULT_RESULT_CALLBACK;
        final ResultCallback resCallBack = callback;
        //UI thread
        callback.onBefore(request);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                sendFailedStringCallback(request, e, resCallBack);
            }

            @Override
            public void onResponse(final Response response) {
                try {
                    final String responseMessage=response.message();
                    final String responseBody = response.body().string();
                    if(response.code()==200){
                        if (resCallBack.mType == String.class) {
                            sendSuccessResultCallback(responseBody, resCallBack);
                        } else {
                            Object o = mGson.fromJson(responseBody, resCallBack.mType);
                            sendSuccessResultCallback(o, resCallBack);
                        }
                    }else{
                        Exception exception=new Exception(response.code()+":"+responseMessage);
                        sendFailedStringCallback(response.request(), exception, resCallBack);
                    }
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, resCallBack);
                } catch (com.google.gson.JsonParseException e) {//Json解析的错误
                    sendFailedStringCallback(response.request(), e, resCallBack);
                }
            }
        });
    }

    /**
     * 处理请求失败的回调信息方法
     * @param request 请求
     * @param e 异常
     * @param callback 回调类
     */
    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        mDelivery.post(() -> {
            callback.onError(request, e);
            callback.onAfter();
        });
    }

    /**
     * 处理请求成功的回调信息方法
     * @param object 服务器响应信息
     * @param callback 回调类
     */
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(() -> {
            callback.onResponse(object);
            callback.onAfter();
        });
    }

    /**
     * 构建Post请求公共方法
     * @param url 服务器响应信息
     * @param params 回调类
     * @param tag 回调类
     */
    private Request buildPostFormRequest(String url, Param[] params, Object tag) {
        if (params == null) {
            params = new Param[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();

        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.url(url)
                .post(requestBody);

        if (tag != null) {
            reqBuilder.tag(tag);
        }
        return reqBuilder.build();
    }

    /*
     **********************************************************
     * 参数实体内部类
     **********************************************************
     */
    public static class Param {
        String key;
        String value;
        public Param() {}
        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /******************************公用的工具方法结束******************************************/

    /*
     **********************************************************
     * Post请求代理内部类
     **********************************************************
     */
    public class PostDelegate {
        private final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream;charset=utf-8");
        private final MediaType MEDIA_TYPE_STRING = MediaType.parse("text/plain;charset=utf-8");

        /**
         * 同步的Post请求
         * @param url
         * @param params
         * @return Response
         */
        public Response post(String url, Param[] params) throws IOException {
            return post(url, params, null);
        }

        /**
         * 通用的基础同步的Post请求
         * @param url
         * @param params
         * @param tag
         * @return Response
         */
        public Response post(String url, Param[] params, Object tag) throws IOException {
            Request request = buildPostFormRequest(url, params, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        /**
         * 同步的Post请求
         * @param url
         * @param params
         * @return String
         */
        public String postAsString(String url, Param[] params) throws IOException {
            return postAsString(url, params, null);
        }

        /**
         * 通用的基础同步的Post请求
         * @param url
         * @param params
         * @param tag
         * @return String
         */
        public String postAsString(String url, Param[] params, Object tag) throws IOException {
            Response response = post(url, params, tag);
            return response.body().string();
        }

        /**
         * 异步的post请求
         * @param url
         * @param params
         * @param callback
         */
        public void postAsyn(String url, Map<String, String> params, final ResultCallback callback) {
            postAsyn(url, params, callback, null);
        }

        /**
         * 异步的post请求
         * @param url
         * @param params
         * @param callback
         * @param tag
         */
        public void postAsyn(String url, Map<String, String> params, final ResultCallback callback, Object tag) {
            Param[] paramsArr = map2Params(params);
            postAsyn(url, paramsArr, callback, tag);
        }

        /**
         * 异步的post请求
         * @param url
         * @param params
         * @param callback
         */
        public void postAsyn(String url, Param[] params, final ResultCallback callback) {
            postAsyn(url, params, callback, null);
        }

        /**
         * 通用基础的异步的post请求
         * @param url
         * @param callback
         * @param tag
         */
        public void postAsyn(String url, Param[] params, final ResultCallback callback, Object tag) {
            Request request = buildPostFormRequest(url, params, tag);
            deliveryResult(callback, request);
        }

        /**
         * 同步的Post请求:直接将bodyStr以写入请求体
         * @param url
         * @param bodyStr
         */
        public Response post(String url, String bodyStr) throws IOException {
            return post(url, bodyStr,null);
        }

        /**
         * 通用基础同步的Post请求:直接将bodyStr以写入请求体
         * @param url
         * @param bodyStr
         * @param tag
         */
        public Response post(String url, String bodyStr, Object tag) throws IOException {
            RequestBody body = RequestBody.create(MEDIA_TYPE_STRING, bodyStr);
            Request request = buildPostRequest(url, body, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        /**
         * 同步的Post请求:直接将bodyFile以写入请求体
         * @param url
         * @param bodyFile
         */
        public Response post(String url, File bodyFile) throws IOException {
            return post(url, bodyFile,null);
        }

        /**
         * 通用基础同步的Post请求:直接将bodyFile以写入请求体
         * @param url
         * @param bodyFile
         * @param tag
         */
        public Response post(String url, File bodyFile, Object tag) throws IOException {
            RequestBody body = RequestBody.create(MEDIA_TYPE_STREAM, bodyFile);
            Request request = buildPostRequest(url, body, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        /**
         * 同步的Post请求:直接将bodyBytes以写入请求体
         * @param url
         * @param bodyBytes
         */
        public Response post(String url, byte[] bodyBytes) throws IOException {
            return post(url, bodyBytes,null);
        }

        /**
         * 通用基础同步的Post请求:直接将bodyBytes以写入请求体
         * @param url
         * @param bodyBytes
         * @param tag
         */
        public Response post(String url, byte[] bodyBytes, Object tag) throws IOException {
            RequestBody body = RequestBody.create(MEDIA_TYPE_STREAM, bodyBytes);
            Request request = buildPostRequest(url, body, tag);
            Response response = mOkHttpClient.newCall(request).execute();
            return response;
        }

        /**
         * 直接将bodyStr以写入请求体
         * @param url
         * @param bodyStr
         * @param callback
         */
        public void postAsyn(String url, String bodyStr, final ResultCallback callback){
            postAsyn(url, bodyStr, callback,null);
        }

        /**
         * 通用的直接将bodyStr以写入请求体
         * @param url
         * @param bodyStr
         * @param callback
         * @param tag
         */
        public void postAsyn(String url, String bodyStr, final ResultCallback callback, Object tag) {
            //postAsynWithMediaType(url, bodyStr, MediaType.parse("text/plain;charset=utf-8"), callback, tag);

            postAsynWithMediaType(url, bodyStr, MediaType.parse("application/json;charset=utf-8"), callback, tag);
        }

        /**
         * 直接将bodyBytes以写入请求体
         * @param url
         * @param bodyBytes
         * @param callback
         */
        public void postAsyn(String url, byte[] bodyBytes, final ResultCallback callback){
            postAsyn(url, bodyBytes, callback, null);
        }

        /**
         * 通用的直接将bodyBytes以写入请求体
         * @param url
         * @param bodyBytes
         * @param callback
         * @param tag
         */
        public void postAsyn(String url, byte[] bodyBytes, final ResultCallback callback, Object tag){
            postAsynWithMediaType(url, bodyBytes, MediaType.parse("application/octet-stream;charset=utf-8"), callback, tag);
        }


        /**
         * 通用的直接将bodyFile以写入请求体
         * @param url
         * @param bodyFile
         * @param callback
         * @param tag
         */
        public void postAsyn(String url, File bodyFile, final ResultCallback callback, Object tag){
            postAsynWithMediaType(url, bodyFile, MediaType.parse("application/octet-stream;charset=utf-8"), callback, tag);
        }

        /**
         * 直接将bodyFile以写入请求体
         * @param url
         * @param bodyFile
         * @param callback
         */
        public void postAsyn(String url, File bodyFile, final ResultCallback callback){
            postAsyn(url, bodyFile, callback, null);
        }

        /**
         * 直接将bodyStr以写入请求体
         * @param url
         * @param bodyStr
         * @param type
         * @param callback
         * @param tag
         */
        public void postAsynWithMediaType(String url, String bodyStr, MediaType type, final ResultCallback callback, Object tag){
            RequestBody body = RequestBody.create(type, bodyStr);
            Request request = buildPostRequest(url, body, tag);
            deliveryResult(callback, request);
        }

        /**
         * 直接将bodyBytes以写入请求体
         * @param url
         * @param bodyBytes
         * @param type
         * @param callback
         * @param tag
         */
        public void postAsynWithMediaType(String url, byte[] bodyBytes, MediaType type, final ResultCallback callback, Object tag){
            RequestBody body = RequestBody.create(type, bodyBytes);
            Request request = buildPostRequest(url, body, tag);
            deliveryResult(callback, request);
        }

        /**
         * 直接将bodyFile以写入请求体
         * @param url
         * @param bodyFile
         * @param type
         * @param callback
         * @param tag
         */
        public void postAsynWithMediaType(String url, File bodyFile, MediaType type, final ResultCallback callback, Object tag){
            RequestBody body = RequestBody.create(type, bodyFile);
            Request request = buildPostRequest(url, body, tag);
            deliveryResult(callback, request);
        }

        /**
         * post构造Request的方法
         * @param url
         * @param body
         * @param tag
         * @return Request
         */
        private Request buildPostRequest(String url, RequestBody body, Object tag) {
            Request.Builder builder = new Request.Builder()
                    .url(url)
                    .post(body);
            if (tag != null) {
                builder.tag(tag);
            }
            Request request = builder.build();
            return request;
        }

    }
    //====================DownloadDelegate=======================

    /*
     **********************************************************
     * Get请求代理内部类
     **********************************************************
     */
    public class GetDelegate {

        /**
         * get构造Request的方法
         * @param url
         * @param tag
         * @return Request
         */
        private Request buildGetRequest(String url, Object tag) {
            MediaType.parse("application/json;charset=utf-8");
            Request.Builder builder = new Request.Builder()
                    .url(url);
            if (tag != null) {
                builder.tag(tag);
            }
            return builder.build();
        }

        /**
         * 通用的的基础同步get方法(返回Response)
         * @param request
         * @return Response
         */
        public Response get(Request request) throws IOException {
            Call call = mOkHttpClient.newCall(request);
            Response execute = call.execute();
            return execute;
        }

        /**
         * 同步的Get请求
         * @param url
         * @return Response
         */
        public Response get(String url) throws IOException {
            return get(url, null);
        }

        /**
         * 同步的Get请求
         * @param url
         * @param tag
         * @return Response
         */
        public Response get(String url, Object tag) throws IOException {
            final Request request = buildGetRequest(url, tag);
            return get(request);
        }

        /**
         * 通用的的基础同步get方法(返回String)
         * @param url
         * @param tag
         * @return String
         */
        public String getAsString(String url, Object tag) throws IOException {
            Response execute = get(url, tag);
            return execute.body().string();
        }

        /**
         * 同步的Get请求
         * @param url
         * @return String
         */
        public String getAsString(String url) throws IOException {
            return getAsString(url, null);
        }

        /**
         * 通用的基础异步get方法
         * @param request
         * @param callback
         */
        public void getAsyn(Request request, ResultCallback callback) {
            deliveryResult(callback, request);
        }

        /**
         * 异步的get请求
         * @param url
         * @param callback
         */
        public void getAsyn(String url, final ResultCallback callback) {
            getAsyn(url, callback, null);
        }

        /**
         * 异步的get请求
         * @param url
         * @param callback
         * @param tag
         */
        public void getAsyn(String url, final ResultCallback callback, Object tag) {
            final Request request = buildGetRequest(url, tag);
            getAsyn(request, callback);
        }
    }

    /**
     * 下载相关的模块
     */
    public class DownloadDelegate {
        /**
         * 异步下载文件
         *
         * @param url
         * @param destFileDir 本地文件存储的文件夹
         * @param destFileDir 本地文件存储的文件名
         * @param callback
         */
        public void downloadAsyn(final String url, final String destFileDir,final String destFileName, final ResultCallback callback, Object tag) {
            final Request request = new Request.Builder()
                    .url(url)
                    .tag(tag)
                    .build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(final Request request, final IOException e) {
                    sendFailedStringCallback(request, e, callback);
                }

                @Override
                public void onResponse(Response response) {
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        is = response.body().byteStream();

                        File dir = new File(destFileDir);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        //File file = new File(dir, getFileName(url));

                        File file = new File(dir,destFileName);
                        fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                        }
                        fos.flush();
                        //如果下载文件成功，第一个参数为文件的绝对路径
                        sendSuccessResultCallback(file.getAbsolutePath(), callback);
                    } catch (IOException e) {
                        sendFailedStringCallback(response.request(), e, callback);
                    } finally {
                        try {
                            if (is != null) is.close();
                        } catch (IOException e) {
                        }
                        try {
                            if (fos != null) fos.close();
                        } catch (IOException e)
                        {
                        }
                    }

                }
            });
        }


        public void downloadAsyn(final String url, final String destFileDir,final String destFileName,  final ResultCallback callback) {
            downloadAsyn(url, destFileDir,destFileName, callback, null);
        }
    }
}