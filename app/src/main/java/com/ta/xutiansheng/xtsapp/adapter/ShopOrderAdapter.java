package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shehuan.niv.NiceImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopOrderAdapter extends RecyclerBaseDapter<ShopMenu, ShopOrderAdapter.ViewHolder> {

    public ShopOrderAdapter(List<ShopMenu> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.orderfoods;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(ShopMenu item, ViewHolder holder, int position) {
            holder.itemView.setTag(item.getFoodid());
            holder.foodname.setText(item.getFoodname());
            new Picasso.Builder(context).build().load(item.getFoodimgs()).into(holder.ivFoodimg);
            holder.tvCount.setText("x " + item.getCount());
            holder.tvPrice.setText("¥ " + item.getPrice());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_foodimg)
        NiceImageView ivFoodimg;
        @BindView(R.id.foodname)
        TextView foodname;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
