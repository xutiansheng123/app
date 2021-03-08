package com.ta.xutiansheng.xtsapp.userInfo;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.Map;

import rx.Subscription;

public class UserInfoModel extends BaseModel implements UserInfoContact.UserInfoModel {
    @Override
    public void getUserInfo(Map<String, Object> map, BaseSubscriber<UserBean> subscriber) {
        Subscription subscribe = mApi.getuserInfo(map).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void updateUserInfo(Map<String, Object> map, BaseSubscriber<Object> subscriber) {
        Subscription subscribe = mApi.updateUserinfo(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
