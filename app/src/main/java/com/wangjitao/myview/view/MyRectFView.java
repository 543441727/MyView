package com.wangjitao.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangjitao on 2016/10/10 0010.
 * 主要是对矩形的使用
 */
public class MyRectFView extends View {
    public MyRectFView(Context context) {
        super(context);
    }

    public MyRectFView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRectFView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFA4C739);

        //绘制机器人的头部（一个半圆弧）
        RectF rectF_head = new RectF(10, 10, 100, 100);
        //设置偏移量
        rectF_head.offset(100, 20);
        //绘制曲线，且去掉最中间的圆心
        canvas.drawArc(rectF_head, -10, -160, false, paint);

        //绘制眼睛（眼睛是两个白色的小圆）
        paint.setColor(Color.WHITE);
        canvas.drawCircle(135, 53, 4, paint);
        canvas.drawCircle(175, 53, 4, paint);

        //绘制两根天线（就是两个直线）
        paint.setColor(0xFFA4C739);
        paint.setStrokeWidth(2);
        canvas.drawLine(120, 15, 135, 35, paint);
        canvas.drawLine(190, 15, 175, 35, paint);

        //绘制身体（一个简单的圆角矩形，使用的是两个矩形，第一个矩形是正常的矩形，第二个矩形是待圆角的矩形）
        canvas.drawRect(110, 75, 200, 150, paint);    //绘制矩形
        RectF rectf_body = new RectF(110, 140, 200, 160);
        canvas.drawRoundRect(rectf_body, 10, 10, paint);    //绘制圆角矩形

        //绘制胳膊（带圆弧的矩形）
        RectF rectf_arm = new RectF(85, 75, 105, 140);
        canvas.drawRoundRect(rectf_arm, 10, 10, paint);    //绘制左侧的胳膊
        rectf_arm.offset(120, 0);                            //设置在X轴上偏移120像素
        canvas.drawRoundRect(rectf_arm, 10, 10, paint);    //绘制右侧的胳膊

        //绘制两条腿（也是两个带矩形的矩形）
        RectF rectf_leg = new RectF(125, 150, 145, 200);
        canvas.drawRoundRect(rectf_leg, 10, 10, paint);    //绘制左侧的腿
        rectf_leg.offset(40, 0);                            //设置在X轴上偏移40像素
        canvas.drawRoundRect(rectf_leg, 10, 10, paint);    //绘制右侧的腿
    }
}
