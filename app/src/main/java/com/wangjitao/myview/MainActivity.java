package com.wangjitao.myview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangjitao.myview.adapter.CityAdapter;
import com.wangjitao.myview.bean.CityBean;
import com.wangjitao.myview.decoration.DividerItemDecoration;
import com.wangjitao.myview.decoration.DividerItemDecorations;
import com.wangjitao.myview.utils.ChineseToPinyinHelper;
import com.wangjitao.myview.view.CollapseView;
import com.wangjitao.myview.view.FlowTagLayout;
import com.wangjitao.myview.view.MyClockView;
import com.wangjitao.myview.view.MyFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private LinearLayout ll_all;
    private CollapseView collapseView1;
    private CollapseView collapseView2;
    private CollapseView collapseView3;
    private FlowTagLayout myFlowLayout;
    private MyClockView mClockView;

    private List<CityBean> mDatas;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        imageView = (ImageView) findViewById(R.id.imageView);
        ll_all = (LinearLayout) findViewById(R.id.ll_all);
        collapseView1 = (CollapseView) findViewById(R.id.collapseView1);
        collapseView2 = (CollapseView) findViewById(R.id.collapseView2);
        collapseView3 = (CollapseView) findViewById(R.id.collapseView3);
        myFlowLayout = (FlowTagLayout) findViewById(R.id.myFlowLayout);
        mClockView = (MyClockView) findViewById(R.id.clockView);
        initData();
//        drawOnBitmap();
//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.entrance1);
//        imageView.setImageBitmap(getRoundCornerBitmap(bm, 500));
    }

    private void initData() {
//        collapseView1.setTitle("baby1");
//        collapseView1.setNumber("1");
//        collapseView1.setContent(R.layout.activity_main);
//
//        collapseView2.setTitle("baby2");
//        collapseView2.setNumber("2");
//        collapseView2.setContent(R.layout.activity_main);
//
//        collapseView3.setTitle("baby3");
//        collapseView3.setNumber("3");
//        collapseView3.setContent(R.layout.activity_main);

//        TextView textView = new TextView(MainActivity.this);
//        textView.setText("你好");
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(20);
//        textView.setGravity(17);
//        textView.setPadding(25, 15, 25, 15);
//        textView.setBackgroundResource(R.drawable.shape_goods_filter_background);
//        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp.leftMargin = 5;
//        lp.rightMargin = 5;
//        lp.topMargin = 5;
//        lp.bottomMargin = 5;
//        textView.setLayoutParams(lp);
//
//        TextView textView1 = new TextView(MainActivity.this);
//        textView1.setText("你好漂亮");
//        textView1.setTextColor(Color.RED);
//        textView1.setTextSize(20);
//        textView1.setLayoutParams(lp);
//        textView1.setGravity(17);
//        textView1.setPadding(25, 15, 25, 15);
//        textView1.setBackgroundResource(R.drawable.shape_goods_filter_background);
//
//        TextView textView3 = new TextView(MainActivity.this);
//        textView3.setText("可以");
//        textView3.setTextColor(Color.RED);
//        textView3.setTextSize(20);
//        textView3.setLayoutParams(lp);
//        textView3.setGravity(17);
//        textView3.setPadding(25, 15, 25, 15);
//        textView3.setBackgroundResource(R.drawable.shape_goods_filter_background);
//
//        TextView textView4 = new TextView(MainActivity.this);
//        textView4.setText("和你");
//        textView4.setTextColor(Color.RED);
//        textView4.setTextSize(20);
//        textView4.setLayoutParams(lp);
//        textView4.setGravity(17);
//        textView4.setPadding(25, 15, 25, 15);
//        textView4.setBackgroundResource(R.drawable.shape_goods_filter_background);
//
//        TextView textVie5 = new TextView(MainActivity.this);
//        textVie5.setText("做朋友吗？");
//        textVie5.setTextColor(Color.RED);
//        textVie5.setTextSize(20);
//        textVie5.setLayoutParams(lp);
//        textVie5.setGravity(17);
//        textVie5.setPadding(25, 15, 25, 15);
//        textVie5.setBackgroundResource(R.drawable.shape_goods_filter_background);
//
//        myFlowLayout.addView(textView, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        myFlowLayout.addView(textView1, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        myFlowLayout.addView(textView3, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        myFlowLayout.addView(textView4, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        myFlowLayout.addView(textVie5, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//        mClockView.setOnTimeChangeListener(new MyClockView.OnTimeChangeListener() {
//            @Override
//            public void onTimeChange(View view, int hour, int minute, int second) {
//            }
//        });
//        mClockView.setTime(11, 59, 50);

        mDatas = new ArrayList<>();
        initDatas(getResources().getStringArray(R.array.provinces));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setAdapter(new CityAdapter(this, mDatas));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, mDatas));
        mRecyclerView.addItemDecoration(new DividerItemDecorations(MainActivity.this, DividerItemDecorations.VERTICAL_LIST));
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(String[] data) {
        mDatas = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data[i]);//设置城市名称
            ;
            cityBean.setTag(ChineseToPinyinHelper.getInstance().getPinyin(data[i]).substring(0,1));
            ;
            mDatas.add(cityBean);
        }
    }

    //利用画布绘制imageView的Bitmap对象
    private void drawOnBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(800, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.GREEN);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        canvas.drawText("hello,Everyone", 150, 200, paint);
        imageView.setImageBitmap(bitmap);
    }

    //实现图片带圆角效果
    public Bitmap getRoundCornerBitmap(Bitmap bitmap, float pixels) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap roundBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, pixels, pixels, paint);
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return roundBitmap;
    }

}
