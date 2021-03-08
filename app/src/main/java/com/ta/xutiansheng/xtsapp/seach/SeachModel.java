package com.ta.xutiansheng.xtsapp.seach;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.List;
import java.util.Map;

import rx.Subscription;

public class SeachModel extends BaseModel implements SeachContact.SeachModel {
    @Override
    public void SeachForKeyWordModel(Map<String, Object> map, BaseSubscriber<List<ShopBean>> subscriber) {
        Subscription subscribe = mApi.searchShopForKeyWord(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
