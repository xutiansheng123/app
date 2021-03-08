package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.ShopOrderAdapter;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.createOrder.CreateOrderContact;
import com.ta.xutiansheng.xtsapp.createOrder.CreateOrderPresent;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class CreatOrderActivity extends BaseMvpActivity<CreateOrderPresent> implements CreateOrderContact.CreateOrderView {
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.tv_nameandphonenum)
    TextView tvNameandphonenum;
    @BindView(R.id.endTime)
    TextView endTime;
    @BindView(R.id.applytype)
    TextView applytype;
    @BindView(R.id.card_userInfo)
    CardView cardUserInfo;
    @BindView(R.id.shopName)
    TextView shopName;
    @BindView(R.id.recycler_foods)
    RecyclerView recyclerFoods;
    @BindView(R.id.con_shop)
    ConstraintLayout conShop;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.tableware)
    TextView tableware;
    @BindView(R.id.con_other)
    ConstraintLayout conOther;
    @BindView(R.id.announcement)
    ConstraintLayout announcement;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.postOrder)
    TextView postOrder;
    @BindView(R.id.allprice)
    TextView allprice;
    private ShopOrderAdapter adaprter;
    private AddressResult bean;
    private String remarks;
    private String foods;
    private String shopId;
    private String allPirce;
    private String names;
    private String shopname;
    Logger logger = LoggerFactory.getLogger(CreatOrderActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foods = getIntent().getStringExtra("foods");
        names = getIntent().getStringExtra("names");
        shopId = getIntent().getStringExtra("shopId");
        shopname = getIntent().getStringExtra("shopname");
        shopName.setText(shopname);
        allPirce = getIntent().getStringExtra("allPirce");
        List<ShopMenu> list = (List<ShopMenu>) getIntent().getSerializableExtra("list");
        adaprter = new ShopOrderAdapter(list, this);
        adaprter.setHasStableIds(true);
        recyclerFoods.setAdapter(adaprter);
        recyclerFoods.setLayoutManager(new LinearLayoutManager(this));
        if (allPirce != null) {
            allprice.setText("¥\t"+allPirce);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String addressID = (String) ShardPrenUtil.getIntance(this).GetParam(ShardPrenUtil.ADDRESSID);
        Map<String, Object> addressmap = new HashMap<>();
        Log.d("TAG", "onResume: "+addressID);
        addressmap.put("addressId", addressID);
        mPresenter.getAddressId(addressmap);
        address.setOnClickListener(view -> {
            startActivityForResult(new Intent(CreatOrderActivity.this, AddressActivity.class), 200);
        });
        adaprter.setOnItemClickListener(position -> {

        });
        //提交订单
        postOrder.setOnClickListener(view -> {
            if (bean == null) {
                ToastUtil.MakeToast(this, "未选择地址");
            } else if (foods == null) {
                ToastUtil.MakeToast(this, "未获取到食品");
            } else if (shopId == null) {
                ToastUtil.MakeToast(this, "未获取到商家信息");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("address", bean.getAddress());
                map.put("phoneNum", bean.getPhonenum());
                //默认未支付宝0
                map.put("applyType", "0");
                map.put("allPrices", allPirce);
                map.put("foodIds", foods);
                map.put("shopId", shopId);
                map.put("foodNames", names);
                map.put("shoname", shopname);
                //收货人姓名
                map.put("name", bean.getUsername());
                map.put("userName", GlobleSettingData.getInstance().getAuthInfo().getUserName());
                if (remarks != null) {
                    map.put("remarks", remarks);
                }
                logger.debug(new Gson().toJson(map));
                mPresenter.createOrderModel(map);
            }
        });
        tvRemarks.setOnClickListener(view -> {
            startActivityForResult(new Intent(CreatOrderActivity.this, RemarksActivity.class), 201);
        });
        ivBack.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected CreateOrderPresent createPresenter() {
        return new CreateOrderPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_order;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200 && data != null) {
            bean = (AddressResult) data.getSerializableExtra("bean");
            address.setText(bean.getAddressname());
            tvNameandphonenum.setText(bean.getUsername() + "\t" + bean.getPhonenum());
            ShardPrenUtil.getIntance(CreatOrderActivity.this).SetParam(ShardPrenUtil.ADDRESSID, String.valueOf(bean.getAddressid()));
        }
        if (resultCode == 201 && data != null) {
            remarks = data.getStringExtra("remarks");
            tvRemarks.setText(remarks);
        }
    }

    @Override
    public void createOrderSuccess(String data) {
        logger.debug(data);
        Intent intent = new Intent(CreatOrderActivity.this, OrderInfoActivity.class);
        intent.putExtra("orderId", data);
        startActivity(intent);
    }

    @Override
    public void createOrderFauile(String msg) {
        ToastUtil.MakeToast(this, msg);
    }

    @Override
    public void getAddressId(AddressResult bean) {
        this.bean = bean;
        address.setText(bean.getAddressname());
        tvNameandphonenum.setText(bean.getUsername()+"\t"+bean.getPhonenum());
    }
}