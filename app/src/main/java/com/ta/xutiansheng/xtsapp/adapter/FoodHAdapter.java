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

public class FoodHAdapter extends RecyclerBaseDapter<ShopMenu, FoodHAdapter.ViewHolder> {

    public FoodHAdapter(List<ShopMenu> foodlist, Context context) {
        this.list = foodlist;
        this.context = context;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.food_item;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }


    @Override
    protected void setmsg(ShopMenu item, ViewHolder holder, int position) {
        if (item!=null){
            if (holder.itemView.getTag()==null||!holder.itemView.getTag().equals(item.getFoodimgs())) {
                Picasso.with(context).load(item.getFoodimgs()).into(holder.niceImageVew);
                holder.itemView.setTag(item.getFoodimgs());
            }
            holder.tvFoodname.setText(item.getFoodname());
        }else {
            holder.tvFoodname.setText("该商品已下架");

        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_foodname)
        TextView tvFoodname;
        @BindView(R.id.niceImageVew)
        NiceImageView niceImageVew;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
