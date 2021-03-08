package com.ta.xutiansheng.xtsapp.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.SeachActivity;
import com.ta.xutiansheng.xtsapp.activity.ShopActivity;
import com.ta.xutiansheng.xtsapp.adapter.RecyclerBaseDapter;
import com.ta.xutiansheng.xtsapp.adapter.ShopListAdapter;
import com.ta.xutiansheng.xtsapp.bean.IpLocationResult;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.index.IndexContact;
import com.ta.xutiansheng.xtsapp.index.IndexPreseneter;
import com.ta.xutiansheng.xtsapp.mvp.BaseFragment;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

public class Indexfragment extends BaseFragment<IndexPreseneter> implements IndexContact.IndexView {

    @BindView(R.id.tv_seach)
    TextView tvSeach;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.recy_type)
    RecyclerView recyType;
    @BindView(R.id.recy_shop)
    RecyclerView recyShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private ShopListAdapter adapter;
    private List<ShopBean> shopBeans;
    private int pageNum = 0;
    private Map<String, String> map;

    @Override
    protected void initview() {
        map = new HashMap<>();
        shopBeans = new ArrayList<>();
        adapter = new ShopListAdapter(context, shopBeans);
        recyShop.setAdapter(adapter);
        recyShop.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected IndexPreseneter createPresenter() {
        return new IndexPreseneter();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.indexfragmeng;
    }

    @Override
    public void onShopListCallBack(List<ShopBean> list) {
        if (pageNum == 0) {
            shopBeans.clear();
        }
        shopBeans.addAll(list);
        adapter.notifyDataSetChanged();
        refreshlayout.finishRefresh();
        refreshlayout.finishLoadMore();
    }

    @Override
    public void onResume() {
        super.onResume();
        mpresneter.GetIpLcationPresent();
        top.setOnClickListener(view -> {
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, new Pair<View, String>(view, "CONTENT"));
            Intent intent = new Intent(context, SeachActivity.class);
            ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
        });
        //通过地理位置获取店铺
        String alt = (String) ShardPrenUtil.getIntance(context).GetParam(ShardPrenUtil.ALTITUDE);
        String lon = (String) ShardPrenUtil.getIntance(context).GetParam(ShardPrenUtil.LATITUDE);
        String adress = "成都";
        map.put("x", alt);
        map.put("y", lon);
        map.put("address", adress);
        map.put("pageSize", "10");
        map.put("pageNum", String.valueOf(pageNum));
        mpresneter.GetShopList(map);
        refreshlayout.setOnRefreshListener(refreshLayout -> {
            pageNum = 0;
            map.put("pageNum", String.valueOf(pageNum));
            mpresneter.GetShopList(map);
        });
        refreshlayout.setOnLoadMoreListener(refreshLayout -> {
            pageNum++;
            map.put("pageNum", String.valueOf(pageNum));
            mpresneter.GetShopList(map);
        });
        adapter.setOnItemClickListener(new RecyclerBaseDapter.OnItemClickListener() {
            @Override
            public void onitemclcike(int position) {
                ShopBean shopBean = shopBeans.get(position);
                Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra("shopId", shopBean.getShopid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onShopListFauile(String msg) {
        ToastUtil.MakeToast(context, msg);
    }

    @Override
    public void onIpLocation(IpLocationResult result) {
        Log.d(TAG, "onIpLocation: "+new Gson().toJson(result));
    }
}
