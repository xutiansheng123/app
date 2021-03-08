package com.ta.xutiansheng.xtsapp.msg;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.MessageBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.List;
import java.util.Map;

public class MessagePresent extends BasePresenter<MessageContanct.MessageView, MessageContanct.MessageModel> implements MessageContanct.MessagePresent {
    @Override
    public void onGetMessagePresent(Map<String, String> map) {
        mModel.onGetMessageModel(map, new BaseSubscriber<List<MessageBean>>() {
            @Override
            public void onSuccess(List<MessageBean> data) {
                getView().onGetMessage(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onFauild(msg);
            }

            @Override
            public void onError() {
                getView().onFauild(ResultType.EerrorCMsg);
            }
        });
    }


    @Override
    protected MessageContanct.MessageModel createModule() {
        return new MessageModel();
    }
}
