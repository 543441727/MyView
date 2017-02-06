package com.qianmo.candle.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import java.util.Random;

/**
 * Created by wangjitao on 2016/12/20 0020.
 * E-Mail：543441727@qq.com
 * 用于实现蜡烛火焰效果
 */
public class Flame {
    //改变因素，不知道是个什么
    private static float CHANGE_FACTOR = 20;
    private Paint mPaint;
    private Path mPath;

    //左下点的坐标
    private int mCurX;
    private int mCurY;

    //火焰宽度
    private int mWidth;
    //火焰高度
    private int mHeight;

    //记录初始的火焰宽高度
    private int mPreWidth;
    private int mPreHeight;

    //火焰顶部贝塞尔曲线控制点变化的参数
    private int mTopXFactor;
    private int mTopYFactor;

    //使用Random随机数产生类实现火焰的抖动
    private Random mRandom;

    //烟球坐标
    private Point mSmokePoint;

    //烟球半径
    private int mSmokeRadius;

    //光环半径
    private int mHaloRadius;

    //是否正在燃烧
    private boolean isFiring;

    //是否启动停止动画
    private boolean mIsStopAnim = false;
    private boolean mFlagStop = false;

    //线性渲染渐变 用于控制火焰的绘制
    private LinearGradient mLinearGradient;

    //环形渲染渐染，用于控制火焰发出的光芒
    private RadialGradient mRadialGradient;

    //火焰动画
    private ValueAnimator mFlameAnimator;

    //光环动画
    private ValueAnimator mHaloAnimator;


    //创建构造函数
    public Flame(int x, int y) {
        mCurX = x;
        mCurY = y;
    }

    //初始化参数
    public void initConfig(int width, int height) {
        mPaint = new Paint();
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPath = new Path();
        mRandom = new Random();
        mSmokePoint = new Point();
        mWidth = width;
        mPreWidth = width;
        mHeight = 0;
        mPreHeight = height;
        mHaloRadius = 70;

        mLinearGradient = new LinearGradient(mCurX + mWidth / 2, mCurY + mPreHeight / 3, mCurX + mWidth / 2, mCurY - mPreHeight / 3 * 4, Color.YELLOW, Color.RED, Shader.TileMode.REPEAT);
        mRadialGradient = new RadialGradient(mCurX + mWidth / 2, mCurY - mPreHeight / 2, mHaloRadius,
                new int[]{Color.WHITE, Color.TRANSPARENT}, null, Shader.TileMode.REPEAT);
        mSmokePoint.x = mCurX - 20;
        mSmokePoint.y = mCurY - 20;

    }

    //初始化动画
    public void initAnim() {
        //在0-4初始值与结束值之间的平滑过度
        mFlameAnimator = ValueAnimator.ofFloat(0, 4).setDuration(4000);
        mFlameAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mFlameAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                float zeroToOne = (float) animator.getAnimatedValue();
                if (zeroToOne >= 1.0f && zeroToOne <= 1.2f) {
                    //让火焰燃起
                    zeroToOne = 1.0f - 5 * (zeroToOne - 1.0f); //0-1之间
                    mHeight = (int) (mPreHeight * (1 - zeroToOne));
                    isFiring = true;
                } else if (zeroToOne >= 3.5f) {
                    if (mFlagStop) {
                        mFlameAnimator.cancel();
                        return;
                    }
                    //火焰被吹灭
                    zeroToOne = 2 * (zeroToOne - 3.5f); //0-2值
                    mTopXFactor = (int) (-20 * zeroToOne);
                    mTopYFactor = (int) (160 * zeroToOne);
                    isFiring = false;
                }
            }
        });

        mFlameAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animator) {
                //当动画重复的时候
                if (mIsStopAnim) {
                    mFlagStop = true;
                }
                mTopYFactor = 0;
                mTopXFactor = 0;
                mHeight = 0;
                mWidth = mPreWidth;
            }
        });

        mHaloAnimator = ValueAnimator.ofFloat(0, 1).setDuration(500);
        mHaloAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mHaloAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float zeroToOne = (float) animation.getAnimatedValue();
                if (isFiring) {
                    mHaloRadius = (int) (70 + zeroToOne % 1.0f * 20);
                }
            }
        });

        //开始动画
        mFlameAnimator.start();
        mHaloAnimator.start();
    }

    /**
     * 停止火焰
     */
    public void stopFlame(){
        mIsStopAnim = true ;
    }

    /**
     * 绘制火焰，使用贝塞尔曲线绘制
     */
    public void drawFlame(Canvas canvas){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(mLinearGradient);
        mPath.reset();
        mPath.moveTo(mCurX,mCurY);
        mPath.quadTo(mCurX + mWidth / 2,
                mCurY + mHeight / 3,
                mCurX + mWidth, mCurY);
        //0.0和1.0之间的float值
        mPath.quadTo(mCurX + mWidth / 2 + ((1 - mRandom.nextFloat()) * CHANGE_FACTOR) + mTopXFactor,
                mCurY - 2 * mHeight + mTopYFactor,
                mCurX, mCurY);
        canvas.drawPath(mPath,mPaint);
        if (isFiring){
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);
            mPaint.setShader(mRadialGradient);
            canvas.drawCircle(mCurX + mWidth / 2, mCurY - mHeight / 2, mHaloRadius, mPaint);
            canvas.drawCircle(mCurX + mWidth / 2, mCurY - mHeight / 2, mHaloRadius + 5, mPaint);
            canvas.drawCircle(mCurX + mWidth / 2, mCurY - mHeight / 2, mHaloRadius - 5, mPaint);
        }
    }

    public int getmCurX() {
        return mCurX;
    }

    public void setmCurX(int mCurX) {
        this.mCurX = mCurX;
    }
}
