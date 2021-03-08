package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.util.StringIsEmptyUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class ShopMenuAdaprter extends RecyclerBaseDapter<ShopMenu, ShopMenuAdaprter.ViewHolder> {

    public ShopMenuAdaprter(Context context, List<ShopMenu> shopMenus) {
        this.context = context;
        this.list = shopMenus;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.shopmenuitem;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    private onItemViewDeltee onItemViewDeltee;

    public void setOnItemViewDeltee(ShopMenuAdaprter.onItemViewDeltee onItemViewDeltee) {
        this.onItemViewDeltee = onItemViewDeltee;
    }

    @Override
    protected void setmsg(ShopMenu item, ShopMenuAdaprter.ViewHolder holder, int position) {
        if (item.getCount() == 0) {
            holder.ivReduction.setVisibility(View.GONE);
        } else if (item.getCount() > 0) {
            holder.ivReduction.setVisibility(View.VISIBLE);
        }
        holder.tvCount.setText(String.valueOf(item.getCount()));
        if (item.getFoodimgs()!=null){
            if (!item.getFoodimgs().equals((String)holder.ivFoodimg.getTag())){
                if (!StringIsEmptyUtil.IsEmpty(item.getFoodimgs())) {
                    holder.ivFoodimg.setTag(item.getFoodimgs());
                    new Picasso.Builder(context).build().load(item.getFoodimgs()).into(holder.ivFoodimg);
                }
            }
        }

        holder.tvTag.setText(item.getInfo());
        holder.tvFoodname.setText(item.getFoodname());
        holder.tvPrice.setText(item.getPrice());
        holder.sales.setText(item.getScale());
        holder.ivAdd.setOnClickListener(view -> {
            onItemViewDeltee.add(position);
        });
        holder.ivReduction.setOnClickListener(view -> {
            onItemViewDeltee.delete(position);
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_foodimg)
        ImageView ivFoodimg;
        @BindView(R.id.iv_add)
        ImageView ivAdd;
        @BindView(R.id.sales)
        TextView sales;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_foodname)
        TextView tvFoodname;
        @BindView(R.id.tv_tag)
        TextView tvTag;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.iv_reduction)
        ImageView ivReduction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemViewDeltee {
        void delete(int postion);

        void add(int postion);
    }
}
