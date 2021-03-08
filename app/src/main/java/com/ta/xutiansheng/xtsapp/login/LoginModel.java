package com.ta.xutiansheng.xtsapp.login;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.bean.LoginResult;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.Subscribers;

public class LoginModel extends BaseModel implements LoginCont.LoginModel {
    @Override
    public void onLogin(Map<String, String> map, Subscriber<LoginResult> subscriber) {

        Subscription subscription = mApi.login(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscription);
    }
}

