package com.wangjitao.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wnagjitao on 2016/10/10 0010.
 * 自定义颜色渐变器
 */
public class MyGradientView extends View {
    public MyGradientView(Context context) {
        super(context);
    }

    public MyGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        //生成线性渐变
        Shader shader = new LinearGradient(0, 0, 50, 50, Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
        //给画笔设置渐变器
        paint.setShader(shader);
        //绘制矩形
        canvas.drawRect(10, 70, 300, 450, paint);

        //生成径向渐变
        shader=new RadialGradient(160, 110, 50, Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        canvas.drawCircle(500, 200, 100, paint);

        //生成角度渐变
        shader=new SweepGradient(265,110,new int[]{Color.RED,Color.GREEN,Color.BLUE},null);
        paint.setShader(shader);
        canvas.drawRect(200, 400, 800, 800, paint);
    }
}
