package com.wangjitao.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class MyPathView extends View {
    public MyPathView(Context context) {
        super(context);
    }

    public MyPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFFF6600);
        paint.setTextSize(26);
        paint.setStyle(Paint.Style.STROKE);

        //绘制折线
        Path path = new Path();
        path.moveTo(200, 300);
        path.lineTo(250,200);
        path.lineTo(300,250);
        path.lineTo(350,400);
        path.lineTo(400,310);
        path.lineTo(500,100);
        canvas.drawPath(path, paint);
//        //绘制三角形路径
//        Path path = new Path();
//        //设置曲线的起点
//        path.moveTo(200, 300);
//        //设置第一条边的结束点
//        path.lineTo(100, 500);
//        //设置第二条边的结束点
//        path.lineTo(300, 500);
//        //闭合路径
//        path.close();
//        paint.setColor(Color.RED);
//        canvas.drawPath(path, paint);
//        //绘制圆形路径
//        Path path = new Path();
//        //添加顺势针圆形路径
//        path.addCircle(400, 400, 100, Path.Direction.CCW);
//        canvas.drawPath(path, paint);

//        //绘制围绕圆形文字
//        Path path = new Path() ;
//        //添加顺时针圆形路径
//        path.addCircle(200,200,100, Path.Direction.CW);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawTextOnPath("床前明月光，疑是地上霜。举头望明月，低头思故乡。",path,0,-18,paint);
    }
}
