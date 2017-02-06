package com.qianmo.eventbustest.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wangjitao on 2016/10/18 0018.
 * 广播测试类
 */
public class MyBroadCastReceiver extends BroadcastReceiver {

    /**
     * 广播按照注册方式分为静态广播和动态广播，静态广播是即使程序退出了接收到
     * 动态广播是一旦程序退出之后就无法接受到该频率下的广播
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我收到广播了啊：" + intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
    }
}
