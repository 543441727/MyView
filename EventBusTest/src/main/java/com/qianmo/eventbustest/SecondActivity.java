package com.qianmo.eventbustest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qianmo.eventbustest.Event.FirstEvent;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {
    private Button mSecondButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mSecondButton = (Button) findViewById(R.id.btn_second);

        mSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FirstEvent("我是界面二"));
            }
        });
    }
}
