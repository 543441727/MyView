package com.qianmo.meituan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianmo.meituan.R;
import com.qianmo.meituan.bean.Subject;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class GridViewAdapter extends BaseAdapter {
    private List<Subject> mData;
    private int currIndex;
    private Context context;
    private int pageSize;
    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, List<Subject> mData, int currIndex, int pageSize) {
        this.context = context;
        this.mData = mData;
        this.currIndex = currIndex;
        this.pageSize = pageSize;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 先要判断是否能填充满这一页
     *
     * @return
     */
    @Override
    public int getCount() {
        return mData.size() > (currIndex + 1) * pageSize ? pageSize : (mData.size() - currIndex * pageSize);
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position + currIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + currIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(mData.get(position + currIndex * pageSize).getName());
        viewHolder.mImageView.setImageResource(mData.get(position + currIndex * pageSize).getIcon_res());
        return convertView;
    }

    public class ViewHolder {
        TextView tv_name;
        ImageView mImageView;
    }
}
