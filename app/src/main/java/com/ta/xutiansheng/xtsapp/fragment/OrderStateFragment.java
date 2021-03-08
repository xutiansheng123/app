package com.ta.xutiansheng.xtsapp.fragment;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.OrderInfoActivity;
import com.ta.xutiansheng.xtsapp.adapter.OrderAdapter;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.mvp.BaseFragment;
import com.ta.xutiansheng.xtsapp.order.OrderContact;
import com.ta.xutiansheng.xtsapp.order.OrderPresent;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

//所有
public class OrderStateFragment extends BaseFragment<OrderPresent> implements OrderContact.OrderView {
    @BindView(R.id.recycler_order)
    RecyclerView recyclerOrder;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private int state;
    private Map<String, Object> map;
    private List<OrderBean> list;
    private OrderAdapter adapter;

    @Override
    protected void initview() {
        list = new ArrayList<>();
        adapter = new OrderAdapter(context, list);
        map = new HashMap<>();
        if (getArguments().get("state") != null) {
            state = getArguments().getInt("state");
            map.put("state", state);
        }
        map.put("userName", ShardPrenUtil.getIntance(context).GetParam(ShardPrenUtil.UserName));
        adapter.setHasStableIds(true);
        recyclerOrder.setAdapter(adapter);
        recyclerOrder.setLayoutManager(new LinearLayoutManager(context));
        recyclerOrder.setClickable(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mpresneter.getOrderList(map);
        refreshlayout.setOnRefreshListener(refreshLayout -> {
            mpresneter.getOrderList(map);
        });
        adapter.setOnItemClickListener(position -> {
            OrderBean orderBean = list.get(position);
            Intent intent = new Intent(context, OrderInfoActivity.class);
            intent.putExtra("orderId", orderBean.getOrderid());
            startActivity(intent);
        });
    }

    @Override
    protected OrderPresent createPresenter() {
        return new OrderPresent();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.alllayoutfragment;
    }

    @Override
    public void getOrderList(List<OrderBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
        refreshlayout.finishRefresh();
    }

    @Override
    public void getOrderFaulier(String msg) {
        refreshlayout.finishRefresh();
        ToastUtil.MakeToast(context,msg);

    }
}
