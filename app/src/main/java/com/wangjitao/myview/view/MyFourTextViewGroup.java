package com.wangjitao.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangjitao on 2016/10/8 0008.
 * 当内部传入TextView的时候依次显示在上下左右的四个角
 */
public class MyFourTextViewGroup extends ViewGroup {

    public MyFourTextViewGroup(Context context) {
        super(context);
    }

    public MyFourTextViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFourTextViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 1,重写generateLayoutParams是view支持margin
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 计算所有子view的宽度和高度，根据ChildView的计算解雇，设置自己的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获得此viewGroup上级容器为其推荐的高和宽，以及计算的方式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //计算出所有的childView的设置的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content设置的宽和高
         */
        int width = 0;
        int height = 0;

        int childCount = getChildCount();

        int childWidth = 0;
        int childHeight = 0;
        MarginLayoutParams childParams = null;

        //用于计算左边两个childView的高度
        int leftHeight = 0;

        //用于计算右边两个childView的高度,最终高度取二者之间的最大值
        int rightHeight = 0;

        //用于取上面两个childView的宽度
        int topWidth = 0;

        //计算下面两个chideView的宽高，取二者之间的最大值
        int bottomWidth = 0;

        /**
         * 根据childView计算出来的宽度和高度，以及设置的margin计算容器的宽和高，主要用于容器的wrap_content
         */
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            childParams = (MarginLayoutParams) childView.getLayoutParams();

            //上面的两个Childview比较的是宽度
            if (i == 0 || i == 1) {
                topWidth += childWidth + childParams.leftMargin + childParams.rightMargin;
            }
            if (i == 2 || i == 3) {
                bottomWidth += childWidth + childParams.leftMargin + childParams.rightMargin;
            }

            //左边的两个childView比较的是高度
            if (i == 0 || i == 2) {
                leftHeight += childHeight + childParams.topMargin + childParams.bottomMargin;
            }
            if (i == 0 || i == 2) {
                rightHeight += childHeight + childParams.topMargin + childParams.bottomMargin;
            }

        }

        width = Math.max(topWidth, bottomWidth);
        height = Math.max(leftHeight, rightHeight);

        //如果是wrap_content则设置为我们计算的值，否的话则设置为父容器计算的值

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width,
                (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);

    }

    /**
     * 用来给所有的childView进行定位
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int childCount = getChildCount();
        int childWidth = 0;
        int childHeight = 0;
        MarginLayoutParams childParams = null;
        /**
         * 遍历所有childView根据其高和宽，以及margin进行布局
         */
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            childParams = (MarginLayoutParams) childView.getLayoutParams();

            int childLeft = 0;
            int childTop = 0;
            int childRight = 0;
            int childBottom = 0;

            switch (i) {
                case 0:
                    childLeft = childParams.leftMargin;
                    childTop = childParams.topMargin;
                    break;
                case 1:
                    Log.i("wangjitao", "getWidth():" + getWidth() + ",childWidth :" + childWidth
                            + ",childParams.leftMargin:" + childParams.leftMargin);
                    childLeft = getWidth() - childWidth -
                            childParams.leftMargin - childParams.rightMargin;
                    childTop = childParams.topMargin;

                    break;
                case 2:
                    childLeft = childParams.leftMargin;

                    childTop = getHeight() - childHeight - childParams.bottomMargin;

                    break;
                case 3:
                    childLeft = getWidth() - childWidth -
                            childParams.leftMargin - childParams.rightMargin;
                    childTop = getHeight() - childHeight - childParams.bottomMargin;
                    break;

            }
            childRight = childLeft + childWidth;
            childBottom = childHeight + childTop;

            childView.layout(childLeft, childTop, childRight, childBottom);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
