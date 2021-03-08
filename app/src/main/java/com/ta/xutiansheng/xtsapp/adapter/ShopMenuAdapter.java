package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopMenuAdapter extends RecyclerBaseDapter<ShopMenu, ShopMenuAdapter.ViewHolder> {
    public ShopMenuAdapter(Context context, List<ShopMenu> list) {
     this.list =list;
     this.context =context;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.serachmenuitem;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(ShopMenu item, ViewHolder holder, int position) {
        Picasso.with(context).load(item.getFoodimgs()).into(holder.imgFood);
        holder.tvFoodname.setText(item.getFoodname());
        holder.tvPrice.setText(item.getPrice());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_food)
        ImageView imgFood;
        @BindView(R.id.tv_foodname)
        TextView tvFoodname;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
