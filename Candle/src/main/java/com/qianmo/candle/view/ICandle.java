package com.qianmo.candle.view;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by wangjitao on 2016/12/20 0020.
 * E-Mail：543441727@qq.com
 * 蜡烛抽象类
 */
public abstract class ICandle {

    //蜡烛左下角坐标
    protected int mCurX;
    protected int mCurY;

    //蜡烛的宽度和高度
    protected int mCandleWidth;
    protected int mCandleHeight;

    //蜡烛左眼坐标
    protected Point mEyeLPoint;
    protected Point mEyeRPoint;

    //蜡烛眼睛半径
    protected int mEyeRadius;

    //眼睛间隔的距离
    protected int mEyeDevide;

    //身体颜色
    protected int mCandleColor;

    //是否停止动画中
    protected boolean mIsAnimStopping = false;

    //蜡烛芯坐标
    protected Point mCandleWickPoint;

    public ICandle(int x, int y) {
        mCurX = x;
        mCurY = y;
    }

    public void initCandle(int width, int height) {
        mCandleHeight = width;
        mCandleWidth = width;
    }

    /**
     * 初始化开始动画
     */
    public void initAnim() {
    }

    /**
     * 结束动画
     */
    public void stopAnim() {
    }

    /**
     * 绘制自己
     *
     * @param canvas
     */
    public void drawSelf(Canvas canvas) {
    }
}
