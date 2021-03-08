package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shehuan.niv.NiceImageView;
import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFoodAdapter extends RecyclerBaseDapter<ShopMenu, OrderFoodAdapter.ViewHolder> {
    public OrderFoodAdapter(Context context, List<ShopMenu> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.orderfoods;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(ShopMenu item, ViewHolder holder, int position) {
        if (item != null) {
            if (holder.itemView.getTag() == null || !holder.itemView.getTag().equals(item.getFoodimgs())) {
                Picasso.with(context).load(item.getFoodimgs()).into(holder.ivFoodimg);
                holder.itemView.setTag(item.getFoodimgs());
            }
            holder.foodname.setText(item.getFoodname());
            holder.tvCount.setText("x\t" + item.getCount());
            holder.tvPrice.setText("¥" + item.getCount() * Float.parseFloat(item.getPrice()));
            holder.foodcontent.setText(item.getInfo());
        }else{
            holder.foodname.setText("该商品已失效");
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_foodimg)
        NiceImageView ivFoodimg;
        @BindView(R.id.foodname)
        TextView foodname;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.foodcontent)
        TextView foodcontent;
        @BindView(R.id.tv_count)
        TextView tvCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
