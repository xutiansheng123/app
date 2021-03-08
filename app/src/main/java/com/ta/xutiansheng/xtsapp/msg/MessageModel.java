package com.ta.xutiansheng.xtsapp.msg;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.MessageBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.util.List;
import java.util.Map;

import rx.Subscription;

public class MessageModel extends BaseModel implements MessageContanct.MessageModel {
    @Override
    public void onGetMessageModel(Map<String, String> map, BaseSubscriber<List<MessageBean>> subscriber) {
        Subscription subscribe = mApi.getMessageList(createRequestBody(new Gson().toJson(map))).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
