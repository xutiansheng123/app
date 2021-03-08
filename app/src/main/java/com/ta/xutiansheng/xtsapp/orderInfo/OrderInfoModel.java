package com.ta.xutiansheng.xtsapp.orderInfo;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.Map;

import rx.Subscription;

public class OrderInfoModel extends BaseModel implements OrderInfoContact.OrderInfoModel {
    @Override
    public void getOrderInfo(Map<String, Object> map, BaseSubscriber<OrderBean> subscriber) {
        Subscription subscribe = mApi.getOrderInfo(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void upDateOrderState(Map<String, Object> map, BaseSubscriber<String> subscriber) {
        if ((int)map.get("state") <6){
            Subscription subscribe = mApi.updateOrderState(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
            addSubscribe(subscribe);
        }else {
            Subscription subscribe = mApi.reOrderstate(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
            addSubscribe(subscribe);
        }
    }
}
