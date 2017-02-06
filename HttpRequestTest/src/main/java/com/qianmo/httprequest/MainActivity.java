package com.qianmo.httprequest;

import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qianmo.httprequest.bean.CommonResultBean;
import com.qianmo.httprequest.bean.UserMenu;
import com.qianmo.httprequest.http.IRequestCallBack;
import com.qianmo.httprequest.http.IRequestManager;
import com.qianmo.httprequest.http.OkHttpClientManager;
import com.qianmo.httprequest.http.RequestFactory;
import com.qianmo.httprequest.http.ResultCallback;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Handler handler;
    private TextView tv_message;
    private Button btn_login;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_message = (TextView) findViewById(R.id.tv_message);
        btn_login = (Button) findViewById(R.id.btn_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        handler = new Handler();
        btn_login.setOnClickListener(this);
//        //测试请求
//        String url = "https://api.douban.com/v2/movie/top250";
//        RequestFactory.getRequestManager().get(url, new IRequestCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                Log.i("wangjitao", response);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                Log.i("wangjitao", throwable.toString());
//            }
//        });

//        //okHttp的基本使用 --- get方法
//        String url = "https://api.douban.com/v2/movie/top250?start=0&count=10";
//        //1,创建OKHttpClient对象
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        //2,创建一个Request
//        Request request = new Request.Builder().url(url).build();
//        //3,创建一个call对象
//        Call call = mOkHttpClient.newCall(request);
//        //4,将请求添加到调度中
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    final String message = response.body().string();
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            tv_message.setText(message);
//                        }
//                    });
//
//                }
//            }
//
//        });

        //okHttp的基本使用 --- post方法带参数
//        String url = "http://192.168.1.123:8081/api/login";
//        //1,创建OKhttpClient对象
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        //2,创建Request
//        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("username", "superadmin");
//        builder.add("pwd", "ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413");
//        Request request = new Request.Builder().url(url).post(builder.build()).build();
//        //3，创建call对象并将请求对象添加到调度中
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.i("wangjitao", response.body().string());
//            }
//        });


    }

    @Override
    public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);


        String url = "http://192.168.1.123:8081/api/login";
        Map<String, String> params = new HashMap();
        params.put("username", "superadmin");
        params.put("pwd", "ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413");
        OkHttpClientManager.postAsyn(url, params,
                new ResultCallback<CommonResultBean<UserMenu>>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(CommonResultBean<UserMenu> response) {
                        if (response.getData() != null) {
                            UserMenu userMenu = response.getData();
                            tv_message.setText(userMenu.getReal_name());
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}
