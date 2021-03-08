package com.ta.xutiansheng.xtsapp.address;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscription;

public class AddressModel extends BaseModel implements AddressContact.AddressModel {

    @Override
    public void onCreateAddress(Map<String, Object> map, BaseSubscriber<Object> baseSubscriber) {
        Subscription subscribe = mApi.createAddress(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(baseSubscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void onDeleteAddressSuccess(Map<String, Object> map, BaseSubscriber<Object> baseSubscriber) {
        Subscription subscribe = mApi.deleteAddress(createRequestBody(new Gson().toJson(map))).compose(RxUtil.handleResult()).compose(RxUtil.applySchedulers()).subscribe(baseSubscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void onGetAddressList(Map<String, Object> map, BaseSubscriber<List<AddressResult>> subscriber) {
        Subscription subscribe = mApi.getAddressList(createRequestBody(new Gson().toJson(map))).compose(RxUtil.handleResult()).compose(RxUtil.applySchedulers()).subscribe(subscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void getSeachAddressList(Map<String, Object> map, BaseSubscriber<List<BaiduResult>> subscriber) {
        Subscription subscribe = mApi.getSeachAddressList(createRequestBody(new Gson().toJson(map))).compose(RxUtil.handleResult()).compose(RxUtil.applySchedulers()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
