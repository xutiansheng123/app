package com.ta.xutiansheng.xtsapp.order;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.List;
import java.util.Map;

import rx.Subscription;

public class OrderModel extends BaseModel implements OrderContact.OrderModel {
    @Override
    public void getOrderList(Map<String, Object> map, BaseSubscriber<List<OrderBean>> subscriber) {
        Subscription subscribe = mApi.getOrderList(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
