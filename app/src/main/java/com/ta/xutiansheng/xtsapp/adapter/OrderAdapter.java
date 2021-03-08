package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shehuan.niv.NiceImageView;
import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

public class OrderAdapter extends RecyclerBaseDapter<OrderBean, OrderAdapter.ViewHolder> {


    public OrderAdapter(Context context, List<OrderBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.order_item;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(OrderBean item, ViewHolder holder, int position) {
        if (holder.itemView.getTag() == null || !holder.itemView.getTag().equals(item.getShopid())) {
            new Picasso.Builder(context).build().load(item.getHeadimg()).into(holder.shopheadImg);
            holder.itemView.setTag(item.getHeadimg());
        }
        holder.allprice.setText("¥" + item.getAllprice());
        holder.tvShopName.setText(item.getShopname());
        holder.tv_count.setText("共" + item.getFoodlist().size() + "件");
        if (item.getState() == 0) {
            holder.tvState.setText("等待商家接单");
        }
        FoodHAdapter foodHAdapter = new FoodHAdapter(item.getFoodlist(), context);
        foodHAdapter.setHasStableIds(true);
        holder.foodrecycler.setAdapter(foodHAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(HORIZONTAL);
        holder.foodrecycler.setLayoutManager(linearLayoutManager);

        switch (item.getState()) {
            case 0:
                //待支付
                holder.aginorder.setText("去支付");
                holder.tvPj.setVisibility(View.GONE);
                holder.refundproess.setVisibility(View.GONE);

                break;
            case 1:
                holder.tvPj.setVisibility(View.GONE);
                holder.refundproess.setVisibility(View.GONE);
                holder.aginorder.setText("点击查看");
                //派送中
                break;
            case 6:
                holder.tvPj.setVisibility(View.VISIBLE);
                holder.refundproess.setVisibility(View.GONE);
                holder.aginorder.setText("再来一单");
                //订单完成
                break;
            case 7:
                holder.aginorder.setVisibility(View.GONE);
                holder.refundproess.setVisibility(View.VISIBLE);
                //订单出意外进行退款或者事后退款
                break;
        }
        switch (item.getState()) {
            case 0:
                holder.tvState.setText("待支付");
                break;
            case 1:
                holder.tvState.setText("等待商家接单");
                break;
            case 2:
                holder.tvState.setText("商家已接单");
                break;
            case 3:
                holder.tvState.setText("商家已出货");
                break;
            case 4:
                holder.tvState.setText("商家请求配送");
                break;
            case 5:
                holder.tvState.setText("骑手正在路上");
                break;
            case 6:
                holder.tvState.setText("骑手正在送货");
                break;
            case 7:
                holder.tvState.setText("已送达");
                break;
            case 8:
                holder.tvState.setText("退款申请中");
                break;
            case 9:
                holder.tvState.setText("退款已到账");
                break;
            case 10:
                holder.tvState.setText("配送异常");
                break;
            case 11:
                holder.tvState.setText("已催单");
                break;
            case 12:
                holder.tvState.setText("订单取消申请中");
                break;
            case 13:
                holder.tvState.setText("订单已取消");
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shopName)
        TextView tvShopName;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.allprice)
        TextView allprice;
        @BindView(R.id.aginorder)
        TextView aginorder;
        @BindView(R.id.tv_pj)
        TextView tvPj;
        @BindView(R.id.refundproess)
        TextView refundproess;
        @BindView(R.id.foodrecycler)
        RecyclerView foodrecycler;
        @BindView(R.id.shopheadImg)
        NiceImageView shopheadImg;

        @BindView(R.id.count)
        TextView tv_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
