package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.MapActivity;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoiResultAdapter extends RecyclerBaseDapter<BaiduResult, PoiResultAdapter.ViewHolder> {

    public PoiResultAdapter(Context c, List<BaiduResult> list) {
        this.list =list;
        this.context =c;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.poiitem;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }
    @Override
    protected void setmsg(BaiduResult item, ViewHolder holder, int position) {
        holder.addressname.setText(item.getName());
        holder.info.setText(item.getAddress());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.addressname)
        TextView addressname;
        @BindView(R.id.info)
        TextView info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
