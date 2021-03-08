package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.UserListAdapter;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.userlist.UserListContace;
import com.ta.xutiansheng.xtsapp.userlist.UserListPresent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class UserListActivity extends BaseMvpActivity<UserListPresent> implements UserListContace.getUserListView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.recyvlerview)
    RecyclerView recyvlerview;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    UserListAdapter adapter;
    List<UserBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        adapter = new UserListAdapter(this, list);
        adapter.setHasStableIds(true);
        recyvlerview.setAdapter(adapter);
        recyvlerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected UserListPresent createPresenter() {
        return new UserListPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        ivBack.setOnClickListener(v -> {
        finish();
        });
        refreshlayout.setOnRefreshListener(refreshLayout -> {
            initData();
        });
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(UserListActivity.this, SendMsgActivity.class);
            intent.putExtra("destUuid", list.get(position).getUuid());
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        super.initData();
        Map<String, String> map = new HashMap<>();
        map.put("uuid", GlobleSettingData.getInstance().getAuthInfo().getUuid());
        mPresenter.onGetUserList(map);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onGetListSuccess(List<UserBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
        Log.d("TAG", "onGetListSuccess: "+new Gson().toJson(this.list));
    }

    @Override
    public void onGetFauild(String msg) {

    }
}