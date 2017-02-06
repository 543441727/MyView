package com.wangjitao.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangjitao on 2016/10/10 0010.
 * 实现流式热门标签(一)
 */
public class MyFlowLayout extends ViewGroup {

    private int verticalSpacing = 20; //两个控件之间的间隙

    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();

        int childStartLayoutX = paddingLeft;
        int childStartLayoutY = paddingTop;

        int widthUsed = paddingLeft + paddingRight;
        int childMaxHeight = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childNeededWidth, childNeedHeight;
                int leftChild, topChild, rightChild, bottomChild;

                int childMeasuredWidth = child.getMeasuredWidth();
                int childMeasuredHeight = child.getMeasuredHeight();

                LayoutParams childLayoutParams = child.getLayoutParams();
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childLayoutParams;
                int childLeftMargin = marginLayoutParams.leftMargin;
                int childTopMargin = marginLayoutParams.topMargin;
                int childRightMargin = marginLayoutParams.rightMargin;
                int childBottomMargin = marginLayoutParams.bottomMargin;

                childNeededWidth = childLeftMargin + childRightMargin + childMeasuredWidth;
                childNeedHeight = childTopMargin + childBottomMargin + childMeasuredHeight;

                if (widthUsed + childNeededWidth <= right - 1) {
                    if (childNeedHeight > childMaxHeight) {
                        childMaxHeight = childNeedHeight;
                    }
                    leftChild = childStartLayoutX + childLeftMargin;
                    topChild = childStartLayoutY + childTopMargin;
                    rightChild = leftChild + childMeasuredWidth;
                    bottomChild = topChild + childMeasuredHeight;

                    widthUsed += childNeededWidth;
                    childStartLayoutX += childNeededWidth;
                } else {
                    childStartLayoutY += childMaxHeight + verticalSpacing;
                    childStartLayoutX = paddingLeft;
                    widthUsed = paddingLeft + paddingRight;
                    leftChild = childStartLayoutX + childLeftMargin;
                    topChild = childStartLayoutY + childTopMargin;
                    rightChild = leftChild + childMeasuredWidth;
                    bottomChild = topChild + childMeasuredHeight;

                    widthUsed += childNeededWidth;
                    childStartLayoutX += childNeededWidth;
                    childMaxHeight = childNeedHeight;
                }
                child.layout(leftChild, topChild, rightChild, bottomChild);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpaceMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();

        //已经使用了的空间
        int widthUsed = paddingLeft + paddingRight;
        int heightUsed = paddingBottom + paddingTop;


        int childMaxHeightOfThisLine = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childUserdWidth = 0;
                int childUserdHeight = 0;
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                childUserdWidth += child.getMeasuredWidth();
                childUserdHeight += child.getMeasuredHeight();

                LayoutParams childLayoutParams = child.getLayoutParams();
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childLayoutParams;

                childUserdWidth += marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                childUserdHeight += marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

                if (widthUsed + childUserdWidth < widthSpaceSize) {
                    widthUsed += childUserdWidth;
                    if (childUserdHeight > childMaxHeightOfThisLine) {
                        childMaxHeightOfThisLine = childUserdHeight;
                    }
                } else {
                    heightUsed += childMaxHeightOfThisLine + verticalSpacing;
                    widthUsed = paddingLeft + paddingRight + childUserdWidth;
                    childMaxHeightOfThisLine = childUserdHeight;
                }
            }
        }

        heightUsed += childMaxHeightOfThisLine;
        setMeasuredDimension(widthSpaceSize, heightUsed);
    }
}

