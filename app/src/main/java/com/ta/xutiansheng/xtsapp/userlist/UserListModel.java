package com.ta.xutiansheng.xtsapp.userlist;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.List;
import java.util.Map;

import rx.Subscription;

public class UserListModel extends BaseModel implements UserListContace.getUserListModel {
    @Override
    public void onGetListModel(Map<String, String> map, BaseSubscriber<List<UserBean>> subscriber) {
        Subscription subscribe = mApi.getUserList(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
