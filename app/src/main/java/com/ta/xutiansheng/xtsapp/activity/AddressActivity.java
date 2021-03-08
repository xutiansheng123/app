package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.AddressAdapter;
import com.ta.xutiansheng.xtsapp.address.AddressContact;
import com.ta.xutiansheng.xtsapp.address.AddressPresent;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class AddressActivity extends BaseMvpActivity<AddressPresent> implements AddressContact.AddressView {

    @BindView(R.id.tv_fw)
    TextView tvFw;
    @BindView(R.id.recycler_addressOk)
    RecyclerView recyclerAddressOk;
    @BindView(R.id.recy_addressFalse)
    RecyclerView recyAddressFalse;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.addAddress)
    TextView addAddress;
    @BindView(R.id.top)
    ConstraintLayout top;
    private Map<String, Object> map;
    private List<AddressResult> list;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected AddressPresent createPresenter() {
        return new AddressPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        map = new HashMap<>();
        list = new ArrayList<>();
        addressAdapter = new AddressAdapter(this, list);
        recyclerAddressOk.setAdapter(addressAdapter);
        recyclerAddressOk.setLayoutManager(new LinearLayoutManager(this));
        map.put("userId", GlobleSettingData.getInstance().getAuthInfo().getUuid());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ivBack.setOnClickListener(view -> {
            finish();
        });
        addressAdapter.setOnItemEditClickListener(position -> {
            Intent intent = new Intent(AddressActivity.this, CreateAddressActivity.class);
            intent.putExtra("bean", list.get(position));
            startActivity(intent);
        });
        addressAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(AddressActivity.this, CreatOrderActivity.class);
            intent.putExtra("bean", list.get(position));
            setResult(200,intent);
            finish();
        });
        mPresenter.onGetAddressList(map);
        addAddress.setOnClickListener(view -> {
            startActivity(new Intent(AddressActivity.this, CreateAddressActivity.class));
        });
    }

    @Override
    public void onCreateAddressSuccess(String msg) {

    }

    @Override
    public void onDeleteAddressSuccess(String msg) {

    }

    @Override
    public void onGetAddressList(List<AddressResult> list) {
        this.list.clear();
        this.list.addAll(list);
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFaulire(String msg) {
        ToastUtil.MakeToast(this, msg);
    }

    @Override
    public void getSeachAddressList(List<BaiduResult> list) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}