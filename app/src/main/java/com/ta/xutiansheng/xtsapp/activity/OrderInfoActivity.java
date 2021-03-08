package com.ta.xutiansheng.xtsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.shehuan.niv.NiceImageView;
import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.OrderFoodAdapter;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.orderInfo.OrderInfoContact;
import com.ta.xutiansheng.xtsapp.orderInfo.OrderInfoPresent;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderInfoActivity extends BaseMvpActivity<OrderInfoPresent> implements OrderInfoContact.OrderInfoView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.ivcancleOrder)
    ImageView ivcancleOrder;
    @BindView(R.id.ivcall_Num)
    ImageView ivcallNum;
    @BindView(R.id.ivrush_order)
    ImageView ivrushOrder;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.ll_frist)
    LinearLayout llFrist;
    @BindView(R.id.ivaftersales)
    ImageView ivaftersales;
    @BindView(R.id.ivcontact)
    ImageView ivcontact;
    @BindView(R.id.ivexceptional)
    ImageView ivexceptional;
    @BindView(R.id.iv_evaluation)
    ImageView ivEvaluation;
    @BindView(R.id.ll_second)
    LinearLayout llSecond;
    @BindView(R.id.distributionType)
    TextView distributionType;
    @BindView(R.id.deliveryTime)
    TextView deliveryTime;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.niceImageVew)
    NiceImageView niceImageVew;
    @BindView(R.id.card_menu)
    CardView cardMenu;
    @BindView(R.id.tv_shopName)
    TextView tvShopName;
    @BindView(R.id.tv_callshop)
    TextView tvCallshop;
    @BindView(R.id.tv_allprice)
    TextView tvAllprice;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.recyclerview_food)
    RecyclerView recyclerviewFood;
    @BindView(R.id.food)
    ConstraintLayout food;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_distributiontype)
    TextView tvDistributiontype;
    @BindView(R.id.tv_distributionname)
    TextView tvDistributionname;
    @BindView(R.id.cent)
    View cent;
    @BindView(R.id.distributioninfo)
    ConstraintLayout distributioninfo;
    @BindView(R.id.tv_orderNum)
    TextView tvOrderNum;
    @BindView(R.id.applytype)
    TextView applytype;
    @BindView(R.id.createTime)
    TextView createTime;
    @BindView(R.id.addresstitle)
    TextView addresstitle;
    @BindView(R.id.title_orderid)
    TextView titleOrderid;
    @BindView(R.id.orderinfo)
    ConstraintLayout orderinfo;
    @BindView(R.id.btn_apply)
    Button btnApply;
    private Map<String, Object> map;
    String orderId;
    Logger logger = LoggerFactory.getLogger(OrderInfoActivity.class);
    private List<ShopMenu> foods;
    private OrderFoodAdapter adapter;
    private OrderBean orderBean;

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected OrderInfoPresent createPresenter() {
        return new OrderInfoPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void initView() {
        foods = new ArrayList<>();
        adapter = new OrderFoodAdapter(this, foods);
        map = new HashMap<>();
        recyclerviewFood.setAdapter(adapter);
        recyclerviewFood.setLayoutManager(new LinearLayoutManager(this));
        orderId = getIntent().getStringExtra("orderId");
        map.put("orderId", orderId);
        map.put("userName", GlobleSettingData.getInstance().getAuthInfo().getUserName());
        mPresenter.getOrderInfo(map);
        ivBack.setOnClickListener(view2 -> {
            finish();
        });
    }

    @Override
    public void getOrderInfo(OrderBean orderBean) {
        this.orderBean = orderBean;
        Picasso.with(this).load(orderBean.getHeadimg()).into(niceImageVew);
        tvAddress.setText(orderBean.getAddress());
        tvDistributionname.setText(orderBean.getDistributionpeoplename());
        tvOrderNum.setText(orderId);
        if (orderBean.getApplytype() == 0) {
            applytype.setText("支付宝");
        } else if (orderBean.getApplytype() == 1) {
            applytype.setText("微信");
        } else {
            applytype.setText("银行卡");
        }
        tvAllprice.setText("¥\t" + orderBean.getAllprice());
        createTime.setText(orderBean.getCreatetime());
        foods.addAll(orderBean.getFoodlist());
        adapter.notifyDataSetChanged();
        if (orderBean.getState() == 0) {
            btnApply.setVisibility(View.VISIBLE);
        } else {
            btnApply.setVisibility(View.GONE);
        }
    }

    @Override
    public void getOrderInfoFauilre(String msg) {
        ToastUtil.MakeToast(this, msg);
    }

    @Override
    public void uPdateOrdersate(String msg) {
        ToastUtil.MakeToast(this, msg);
        mPresenter.getOrderInfo(map);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btnApply.setOnClickListener(v -> {
            if (orderBean != null) {
                //提交订单
                Map<String, Object> map = new HashMap<>();
                map.put("orderId", orderId);
                map.put("state", orderBean.getState());
                map.put("userId", GlobleSettingData.getInstance().getAuthInfo().getUuid());
                map.put("loginType",0);
                mPresenter.upDateOrderState(map);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}