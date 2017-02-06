package com.wangjitao.myview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.wangjitao.myview.R;

/**
 * Created by wangjitao on 2016/10/9 0009.
 * 用于实现textview回执线条
 */
public class TextDraw extends View {
    public TextDraw(Context context) {
        super(context);
    }

    public TextDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
//        //绘制白色的矩形
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(0, 0, 800, 800, paint);
//        paint.reset();
//
//        //绘制直线
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(10);
//        canvas.drawLine(450, 30, 570, 170, paint);
//        paint.reset();
//
//        //绘制带边框的矩形
//        paint.setStrokeWidth(10);
//        paint.setARGB(150, 90, 255, 0);
//        paint.setStyle(Paint.Style.STROKE);
//
//        RectF rectF = new RectF(30, 60, 350, 350);
//        canvas.drawRect(rectF, paint);
//        paint.reset();
//
//        //绘制实心圆
//        paint.setStrokeWidth(14);
//        paint.setColor(Color.GREEN);
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(670, 300, 70, paint);
//        paint.reset();
//        //绘制椭圆
//        paint.setColor(Color.BLUE);
//        RectF rectF1 = new RectF(200, 430, 600, 600);
//        canvas.drawOval(rectF1, paint);
//        paint.reset();
//
//        //绘制空心圆
//        paint.setStrokeWidth(10);
//        paint.setColor(Color.BLACK);
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(80, 500, 70, paint);
//
//        //绘制文字
//        paint.setColor(Color.RED);
//        paint.setTextSize(60);
//        paint.setUnderlineText(true);
//        paint.setAntiAlias(true);
//        canvas.drawText("wangjitao", 150, 720, paint);
//        paint.reset();

        //调用canvas.translate()实现内容的平移
//        canvas.drawColor(Color.GREEN);
//        paint.setTextSize(60);
//        paint.setColor(Color.BLUE);
//        canvas.drawText("蓝色字体平移之前", 20, 80, paint);
//        canvas.translate(100, 300);
//        paint.setColor(Color.BLACK);
//        canvas.drawText("蓝色字体平移之后", 20, 80, paint);

        //调用canvas.rotate()方法实现内容物的旋转
//        canvas.drawColor(Color.GREEN);
//        paint.setTextSize(60);
//        paint.setColor(Color.BLUE);
//        canvas.drawText("wangjitao", 20, 80, paint);
//        canvas.rotate(15);
//        paint.setColor(Color.RED);
//        canvas.drawText("wangjitao", 20, 80, paint);

        //调用canvas.clipRect(),实现对view的剪裁
//        canvas.drawColor(Color.GREEN);
//        paint.setTextSize(60);
//        paint.setColor(Color.BLUE);
//        canvas.drawText("剪裁前的区域", 20, 80, paint);
//        Rect rect = new Rect(20, 200, 900, 1000);
//        canvas.clipRect(rect);
//        canvas.drawColor(Color.RED);
//        paint.setColor(Color.BLACK);
//        canvas.drawText("剪裁之后的区域", 40, 310, paint);

        //实现对view 的两层画布调用canvas.save() 和canvas.restore()方法
//        canvas.drawColor(Color.GREEN);
//        paint.setTextSize(60);
//        paint.setColor(Color.BLUE);
//        canvas.drawText("这是未被剪裁的区域", 20, 80, paint);
//        canvas.save();
//        Rect rect = new Rect(20, 200, 900, 1000);
//        canvas.clipRect(rect);
//        canvas.drawColor(Color.YELLOW);
//        paint.setColor(Color.BLACK);
//        canvas.drawText("这是被剪裁后的区域", 10, 310, paint);
//        canvas.restore();
//        paint.setColor(Color.RED);
//        canvas.drawText("放心", 20, 170, paint);

        //实现图片的圆角


        //2,使用matrix实现图片的旋转缩放
//        paint.setAntiAlias(true);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.baby);
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        Matrix matrix = new Matrix();
//        canvas.drawBitmap(bitmap, matrix, paint);
//        matrix.setTranslate(width / 2, height);
//        canvas.drawBitmap(bitmap, matrix, paint);
//        matrix.postScale(0.5f, 0.5f);
//        canvas.drawBitmap(bitmap, matrix, paint);

        //3,使用shader达到图像的渐变效果，主要用于图像和几何图形的渲染（实现图片小头像）
//        paint.setAntiAlias(true);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.baby);
//        int radius = bitmap.getWidth() / 2;
//        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        paint.setShader(bitmapShader);
//        canvas.translate(250, 430);
//        canvas.drawCircle(radius, radius, radius, paint);

        //4,使用PathEffrct来绘制一些曲线，先使用CornerPathEffect、DashPathEffect(效果是折线图)
        canvas.translate(0, 300);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(15, 60);
        for (int i = 0; i < 35; i++) {
            path.lineTo(i * 30, (float) (Math.random() * 150));
        }
        canvas.drawPath(path, paint);

        canvas.translate(0, 400);
        paint.setPathEffect(new CornerPathEffect(60));
        canvas.drawPath(path, paint);

        canvas.translate(0, 400);
        paint.setPathEffect(new DashPathEffect(new float[]{15, 8}, 1));
        canvas.drawPath(path, paint);

    }
}
