package com.wangjitao.myview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjitao.myview.R;
import com.wangjitao.myview.bean.CityBean;

import java.util.List;

/**
 * Created by wangjitao on 2016/10/12 0012.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHold> {

    private Context mContext;
    private List<CityBean> mDatas;
    private LayoutInflater mInflater;

    public CityAdapter(Context context, List<CityBean> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CityAdapter.ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHold(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHold holder, final int position) {
        holder.tvCity.setText(mDatas.get(position).getCity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mDatas.get(position).getCity(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        TextView tvCity;

        public ViewHold(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
        }
    }
}
