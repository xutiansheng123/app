package com.ta.xutiansheng.xtsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class RecyclerBaseDapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected OnItemClickListener onItemClickListener;
    protected List<T> list;
    protected LayoutInflater inflater;
    protected Context context;
    protected RecyclerView.ViewHolder viewHolder;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(getlayoutid(viewType),parent,false);
        viewHolder = creatholder(v,viewType);
        return viewHolder;
    }


    @Override
    public long getItemId(int position) {
        return  position;
    }
    protected abstract int getlayoutid(int viewType);

    @Override
    public int getItemCount() {
        return list.size();
    }


        protected abstract V creatholder(View v, int viewType);


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        T item = list.get(position);
        setmsg(item, (V) holder, position);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onitemclcike(position);
            }
        });
    }

    protected abstract void setmsg(T item, V holder, int position);

    public interface OnItemClickListener {
        void onitemclcike(int position);
    }
}
