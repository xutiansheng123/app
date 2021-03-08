package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shehuan.niv.NiceImageView;
import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListAdapter extends RecyclerBaseDapter<ShopBean,ShopListAdapter.ViewHolder> {
    public ShopListAdapter(Context context, List<ShopBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.shopitem;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(ShopBean item, ViewHolder holder, int position) {
        if (!item.getShopid().equals(holder.itemView.getTag())){
            new Picasso.Builder(context).build().load(item.getHeadimg()).into(holder.shopIcon);
            holder.itemView.setTag(item.getShopid());
        }
        holder.shopName.setText(item.getShopname());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shopIcon)
        NiceImageView shopIcon;
        @BindView(R.id.shopName)
        TextView shopName;
        @BindView(R.id.score)
        TextView score;
        @BindView(R.id.dx)
        TextView dx;
        @BindView(R.id.minSend)
        TextView minSend;
        @BindView(R.id.evaluation)
        TextView evaluation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
