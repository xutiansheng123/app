package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.SeachShopAdapter;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.seach.SeachContact;
import com.ta.xutiansheng.xtsapp.seach.SeachPresent;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class SeachActivity extends BaseMvpActivity<SeachPresent> implements SeachContact.SeachView {
    @BindView(R.id.edt_seach)
    EditText edtSeach;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.recyvler)
    RecyclerView recyvler;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private List<ShopBean> list;
    private SeachShopAdapter adapter;
    private int pageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        adapter = new SeachShopAdapter(list, this);
        adapter.setHasStableIds(true);
        recyvler.setAdapter(adapter);
        recyvler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtSeach.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    pageNum = 0;
                    initData();
                    hideKeyInput();
                }
                return true;
            }
        });
        tvBack.setOnClickListener(view -> {
            finishAfterTransition();
        });
        refreshlayout.setOnLoadMoreListener(refreshLayout -> {
            pageNum++;
            initData();
        });
        adapter.setOnItemClickListener(position -> {
            Intent intent =new Intent(SeachActivity.this,ShopActivity.class);
            intent.putExtra("shopId",list.get(position).getShopid());
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        super.initData();
        Map<String, Object> map = new HashMap<>();
        map.put("address", "成都");
        map.put("x", ShardPrenUtil.getIntance(this).GetParam(ShardPrenUtil.ALTITUDE));
        map.put("y", ShardPrenUtil.getIntance(this).GetParam(ShardPrenUtil.LATITUDE));
        map.put("pageNum", pageNum);
        map.put("pageSize", 10);
        String keyWord = edtSeach.getText().toString();
        if (!"".equals(replace(keyWord))) {
            map.put("keyWord", keyWord);
        }
        mPresenter.SeachForKeyWordPresent(map);
        refreshlayout.finishLoadMore();
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected SeachPresent createPresenter() {
        return new SeachPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seach;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void SeachForKeyWordSuccess(List<ShopBean> list) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFauiler(String msg) {

    }
}