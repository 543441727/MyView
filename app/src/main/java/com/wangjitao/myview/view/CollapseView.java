package com.wangjitao.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjitao.myview.R;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class CollapseView extends LinearLayout {
    private long duration = 350; //动画持续的时间
    private Context mContext;
    private TextView mNumberTextView; //数量的textview
    private TextView mTitleTextView; //标题的texiview
    private RelativeLayout mContentRelativeLayout;
    private RelativeLayout mTitleRelativeLayout;
    private ImageView mArrowImageView;
    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;


    public CollapseView(Context context) {
        this(context, null);
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.activity_main2, this);
        initView();
    }

    private void initView() {
        mNumberTextView = (TextView) findViewById(R.id.numberTextView);
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mTitleRelativeLayout = (RelativeLayout) findViewById(R.id.titleRelativeLayout);
        mContentRelativeLayout = (RelativeLayout) findViewById(R.id.contentRelativeLayout);
        mArrowImageView = (ImageView) findViewById(R.id.arrowImageView);
        mTitleRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateArrow();
            }
        });
    }

    public CollapseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNumber(String number){
        if(!TextUtils.isEmpty(number)){
            mNumberTextView.setText(number);
        }
    }

    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTitleTextView.setText(title);
        }
    }

    public void setContent(int resID){
        View view=LayoutInflater.from(mContext).inflate(resID,null);
        RelativeLayout.LayoutParams layoutParams=
                new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        mContentRelativeLayout.addView(view);
    }


    public void rotateArrow() {
        int degree = 0;
        if (mArrowImageView.getTag() == null || mArrowImageView.getTag().equals(true)) {
            mArrowImageView.setTag(false);
            degree = -180;
            expand(mContentRelativeLayout);
        } else {
            degree = 0;
            mArrowImageView.setTag(true);
            collaspe(mContentRelativeLayout);
        }
        //将小图标旋转180度
        mArrowImageView.animate().setDuration(duration).rotation(degree);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentHeightMeasureSpec = heightMeasureSpec;
        parentWidthMeasureSpec = widthMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void collaspe(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = measuredHeight - (int) (measuredHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    private void expand(final View view) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        view.measure(parentWidthMeasureSpec,parentHeightMeasureSpec);
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        view.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    view.getLayoutParams().height =measuredHeight;
                }else{
                    view.getLayoutParams().height =(int) (measuredHeight * interpolatedTime);
                }
                view.requestLayout();
            }


            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }
}
