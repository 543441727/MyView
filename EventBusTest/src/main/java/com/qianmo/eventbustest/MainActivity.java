package com.qianmo.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qianmo.eventbustest.Event.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private Button mFirstButton;
    private TextView mTextCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        mFirstButton = (Button) findViewById(R.id.btn_first);
        mTextCallBack = (TextView) findViewById(R.id.tv_sendCallBcak);

        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInformation();
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });



    }

    private void sendInformation() {
        Intent intent = new Intent();
        intent.putExtra("name", "wangjitao");
        intent.setAction("wjt");
        sendBroadcast(intent);
    }

    @Subscribe
    public void onEventMainThread(FirstEvent firstEvent) {
        Toast.makeText(MainActivity.this, firstEvent.getMsg(), Toast.LENGTH_SHORT).show();
        mTextCallBack.setText(firstEvent.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
