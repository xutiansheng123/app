package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shehuan.niv.NiceImageView;
import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.SeachActivity;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeachShopAdapter extends RecyclerBaseDapter<ShopBean, SeachShopAdapter.ViewHolder> {


    public SeachShopAdapter(List<ShopBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.seachshopitem;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(ShopBean item, ViewHolder holder, int position) {
        if (holder.itemView.getTag()==null||!holder.itemView.getTag().equals(item.getHeadimg())) {
            holder.itemView.setTag(item.getHeadimg());
            Picasso.with(context).load(item.getHeadimg()).into(holder.shopheadImg);
        }
        holder.tvShopName.setText(item.getShopname());
        ShopMenuAdapter adaprter = new ShopMenuAdapter(context, item.getFoodlist());
        holder.recyMenu.setAdapter(adaprter);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        holder.recyMenu.setLayoutManager(manager);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shopName)
        TextView tvShopName;
        @BindView(R.id.tv_score)
        TextView tvScore;
        @BindView(R.id.sales)
        TextView sales;
        @BindView(R.id.startPrice)
        TextView startPrice;
        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.shopheadImg)
        NiceImageView shopheadImg;
        @BindView(R.id.recy_menu)
        RecyclerView recyMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
