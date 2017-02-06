package com.qianmo.VerificationCode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.qianmo.VerificationCode.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by wangjitao on 2016/10/13 0013.
 * 用于实现获取随机码
 */
public class MyTextView extends View {
    /**
     * 由于是自定义的View，首先我们要确定那些属性是用户可以自己定义的
     * 1，View里面显示的字
     * 2，显示字的大小
     * 3，显示字的颜色
     */

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * view的矩形背景
     */
    private Rect mBound;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获得自定义的属性
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获得我们自定义的一些属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0);
        mTitleText = randomText(); //初始化显示的数字

        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyTextView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.MyTextView_titleTextColor:
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MyTextView_titleTextSize:
                    //设置默认大小为16
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        //将TypedArray对象回收
        a.recycle();

        /**
         * 初始化画笔
         */
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        /**
         * 模仿点击换验证码
         */
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleText = randomText();
                postInvalidate();
            }
        });
    }

    /**
     * 获取四位随机数验证码
     *
     * @return
     */
    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 处理当宽高都是wrap_content的情况
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.height();
            int desired = (int) (getPaddingTop() + textWidth + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 绘制文字和矩形
         */
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
