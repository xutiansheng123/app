package com.ta.xutiansheng.xtsapp.createOrder;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.Map;

import rx.Subscription;

public class CreateOrderModel extends BaseModel implements CreateOrderContact.CreateOrderModel {
    @Override
    public void createOrderModel(Map<String, Object> map, BaseSubscriber<String> subscriber) {
        Subscription subscribe = mApi.createOrder(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void getAddressId(Map<String, Object> map, BaseSubscriber<AddressResult> subscriber) {
        Subscription subscribe = mApi.getAddressForId(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
