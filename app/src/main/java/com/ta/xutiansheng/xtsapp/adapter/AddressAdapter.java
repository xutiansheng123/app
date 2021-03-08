package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.AddressActivity;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressAdapter extends RecyclerBaseDapter<AddressResult, AddressAdapter.ViewHolder> {
   private OnItemEditClickListener onItemEditClickListener;
    public AddressAdapter(Context context, List<AddressResult> list) {
        this.list =list;
        this.context=context;
    }

    public void setOnItemEditClickListener(OnItemEditClickListener onItemEditClickListener) {
        this.onItemEditClickListener = onItemEditClickListener;
    }

    @Override
    protected int getlayoutid(int viewType) {
        return R.layout.addresss_item;
    }

    @Override
    protected ViewHolder creatholder(View v, int viewType) {
        return new ViewHolder(v);
    }

    @Override
    protected void setmsg(AddressResult item, ViewHolder holder, int position) {
        holder.addressname.setText(item.getAddressname());
        holder.info.setText(item.getAddressinfo());
        holder.tvNameandphonenum.setText(item.getUsername() + "\t\t" + item.getPhonenum());
        holder.ivEdit.setOnClickListener(view -> {
            onItemEditClickListener.onitemClickListener(position);
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.addressname)
        TextView addressname;
        @BindView(R.id.info)
        TextView info;
        @BindView(R.id.tv_nameandphonenum)
        TextView tvNameandphonenum;
        @BindView(R.id.iv_edit)
        ImageView ivEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemEditClickListener{
        void onitemClickListener(int position);
    }
}
