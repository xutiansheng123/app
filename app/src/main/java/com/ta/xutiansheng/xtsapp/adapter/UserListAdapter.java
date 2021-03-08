package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.UserListActivity;
import com.ta.xutiansheng.xtsapp.bean.UserBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserListAdapter extends RecyclerBaseDapter<UserBean, UserListAdapter.ViewHolder> {


    public UserListAdapter(Context context, List<UserBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.userlist_item;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(UserBean item, ViewHolder holder, int position) {
        if (holder.ivHeadimg.getTag() == null || !item.getHeadimg().equals((String) holder.ivHeadimg.getTag())) {
            Picasso.with(context).load(item.getHeadimg()).into(holder.ivHeadimg);
            holder.ivHeadimg.setTag(item.getHeadimg());
        }
        holder.userName.setText(item.getNickname());
        holder.tvMsg.setText("");
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.tv_msg)
        TextView tvMsg;
        @BindView(R.id.iv_headimg)
        CircleImageView ivHeadimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
