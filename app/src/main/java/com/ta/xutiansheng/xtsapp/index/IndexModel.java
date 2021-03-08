package com.ta.xutiansheng.xtsapp.index;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.IpLocationResult;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;

public class IndexModel extends BaseModel implements IndexContact.IndexModel {
    @Override
    public void GetShopList(Subscriber<List<ShopBean>> subscriber, Map<String, String> map) {
        Subscription subscribe = mApi.getShopList(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void GetIpLocation(Subscriber<IpLocationResult> subscriber) {
        Subscription subscribe = mApi.locationIp().compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
