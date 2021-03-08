package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shehuan.niv.NiceImageView;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.SendMsgActivity;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.MessageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerBaseDapter<MessageBean, MessageAdapter.ViewHolder> {


    public MessageAdapter(Context con, List<MessageBean> list) {
        this.context =con;
        this.list=list;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.message_item;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(MessageBean item, ViewHolder holder, int position) {
        if (item.getUuid().equals(GlobleSettingData.getInstance().getAuthInfo().getUuid())) {
            holder.ivMineimg.setVisibility(View.VISIBLE);
            holder.mineText.setVisibility(View.VISIBLE);
            holder.ivHeadimg.setVisibility(View.GONE);
            holder.otherMsg.setVisibility(View.GONE);
            holder.mineText.setText(item.getMsg());
        } else {
            holder.ivMineimg.setVisibility(View.GONE);
            holder.mineText.setVisibility(View.GONE);
            holder.ivHeadimg.setVisibility(View.VISIBLE);
            holder.otherMsg.setVisibility(View.VISIBLE);
            holder.otherMsg.setText(item.getMsg());

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_headimg)
        NiceImageView ivHeadimg;
        @BindView(R.id.other_msg)
        TextView otherMsg;
        @BindView(R.id.iv_mineimg)
        NiceImageView ivMineimg;
        @BindView(R.id.mine_text)
        TextView mineText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
