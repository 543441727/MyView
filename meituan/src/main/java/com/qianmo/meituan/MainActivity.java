package com.qianmo.meituan;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qianmo.meituan.adapter.GridViewAdapter;
import com.qianmo.meituan.adapter.ViewPagerAdapter;
import com.qianmo.meituan.bean.Subject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext = MainActivity.this;
    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};
    private LinearLayout ll_dot;
    private ViewPager mViewPager;
    private List<Subject> datas;
    private List<View> mViews;
    private LayoutInflater mLayoutInflater;
    private ViewPagerAdapter mViewPagerAdapter;

    private int curIndex;
    private int pageCount;
    private final static int PAGESIZE = 10;  //每一页展示图片的数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //初始化data
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            datas.add(new Subject(titles[i], imageId));
        }
        mLayoutInflater = LayoutInflater.from(mContext);
        //判断需要多少页来展示所有的图标
        pageCount = (int) Math.ceil(datas.size() * 1.0f / PAGESIZE);
        for (int i = 0; i < pageCount; i++) {
            GridView gridView = (GridView) mLayoutInflater.inflate(R.layout.gridview, mViewPager, false);
            gridView.setAdapter(new GridViewAdapter(mContext, datas, i, PAGESIZE));
            mViews.add(gridView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    int pos = position + curIndex * PAGESIZE;
                    Toast.makeText(MainActivity.this, datas.get(pos).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        mViewPagerAdapter = new ViewPagerAdapter(mViews);
        mViewPager.setAdapter(mViewPagerAdapter);
        initDot();
    }

    private void initDot() {
        for (int i = 0; i < pageCount; i++) {
            ll_dot.addView(mLayoutInflater.inflate(R.layout.dot, null));
        }
        //默认显示第一页
        ll_dot.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { //现在使用addOnPageChangeListener代替
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 取消圆点选中
                ll_dot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                ll_dot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        ll_dot = (LinearLayout) findViewById(R.id.ll_dot);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        datas = new ArrayList<>();
        mViews = new ArrayList<>();
    }


}
