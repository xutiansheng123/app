package com.ta.xutiansheng.xtsapp.shopInfo;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;

public class ShopMenuModel extends BaseModel implements ShopInfoContact.ShopModel {


    @Override
    public void ongetShopMenu(String shopId, Subscriber<List<ShopMenu>> subscriber) {
        Map<String, String> map = new HashMap<>();
        map.put("shopId", shopId);
        Subscription subscribe = mApi.getMenuListForshopId(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void getShopInfo(Map<String, String> map, Subscriber<ShopBean> subscriber) {
        Subscription subscribe = mApi.getShopIfo(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
