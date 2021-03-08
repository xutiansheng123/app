package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.ShopMenuAdaprter;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.shopInfo.ShopInfoContact;
import com.ta.xutiansheng.xtsapp.shopInfo.ShopPresenter;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ShopActivity extends BaseMvpActivity<ShopPresenter> implements ShopInfoContact.ShopInfoView {
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.ivicon)
    ImageView ivicon;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_psf)
    TextView tvPsf;
    @BindView(R.id.bottom)
    ConstraintLayout bottom;
    @BindView(R.id.recycler_menu)
    RecyclerView recyclerMenu;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    private String shopId;
    private List<ShopMenu> list;
    private ShopMenuAdaprter adaprter;
    Logger logger = LoggerFactory.getLogger(ShopActivity.class);
    private ShopBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopId = getIntent().getStringExtra("shopId");
        list = new ArrayList<>();
        adaprter = new ShopMenuAdaprter(this, list);
        adaprter.setHasStableIds(true);
        recyclerMenu.setLayoutManager(new LinearLayoutManager(this));
        recyclerMenu.setAdapter(adaprter);
        btnSubmit.setEnabled(false);
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //提交订单功能
        btnSubmit.setOnClickListener(view -> {
            //TODO 提交订单
            float allprice = 0.0f;
            StringBuilder builder = new StringBuilder();
            StringBuilder names = new StringBuilder();
            List<ShopMenu> sumbit = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCount() > 0) {
                    sumbit.add(list.get(i));
                    allprice += (Float.parseFloat(list.get(i).getPrice()) * list.get(i).getCount());
                    if ("".equals(builder.toString())) {
                        names.append(list.get(i).getFoodname());
                        builder.append(list.get(i).getFoodid());
                        builder.append(":");
                        builder.append(list.get(i).getCount());
                    } else {
                        names.append(",");
                        names.append(list.get(i).getFoodname());
                        builder.append(",");
                        builder.append(list.get(i).getFoodid());
                        builder.append(":");
                        builder.append(list.get(i).getCount());
                    }
                }
            }
            Intent intent = new Intent(this, CreatOrderActivity.class);
            intent.putExtra("foods", builder.toString());
            intent.putExtra("names", names.toString());
            intent.putExtra("shopname", bean.getShopname());
            intent.putExtra("list", (Serializable) sumbit);
            intent.putExtra("shopId", this.shopId);
            intent.putExtra("allPirce", String.valueOf(allprice));
            if (sumbit.size() > 0 && bean != null) {
                startActivity(intent);
            }
        });
        //增加或者减去
        adaprter.setOnItemViewDeltee(new ShopMenuAdaprter.onItemViewDeltee() {
            @Override
            public void delete(int postion) {
                ShopMenu shopMenu = list.get(postion);
                if (shopMenu.getCount() > 0) {
                    shopMenu.setCount(shopMenu.getCount() - 1);
                    list.set(postion, shopMenu);
                    adaprter.notifyItemChanged(postion);
                    reCalulate();
                }
            }

            @Override
            public void add(int postion) {
                ShopMenu shopMenu = list.get(postion);
                shopMenu.setCount(shopMenu.getCount() + 1);
                list.set(postion, shopMenu);
                adaprter.notifyItemChanged(postion);
                reCalulate();

            }
        });
        ivBack.setOnClickListener(view -> {
            finish();
        });
        Map<String, String> map = new HashMap<>();
        map.put("shopId", shopId);
        mPresenter.getShopInfo(map);
        mPresenter.ongetShopMenuForShopid(shopId);
    }

    @Override
    protected void initView() {
    }

    @Override
    public void ongetShopMenu(List<ShopMenu> data) {
        list.clear();
        list.addAll(data);
        logger.debug(new Gson().toJson(list));
        adaprter.notifyDataSetChanged();
    }

    @Override
    public void ongetShopInfo(ShopBean bean) {
        this.bean = bean;
    }

    private void reCalulate() {
        float price = 0.00f;
        for (ShopMenu menu :
                list) {
            if (menu.getCount() > 0) {
                price += menu.getCount() * Double.parseDouble(menu.getPrice());
            }
        }
        tvPrice.setText("¥" + price);
        //获取店铺详情起送背景图片等
        if (price > 0) {
            btnSubmit.setEnabled(true);
        }
    }


    @Override
    public void onFauiled(String msg) {
        ToastUtil.MakeToast(this, msg);
    }
}