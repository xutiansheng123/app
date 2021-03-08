package com.ta.xutiansheng.xtsapp.msg;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.MessageBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.List;
import java.util.Map;

public class MessageContanct {
    public interface MessageView extends IView {
        void onGetMessage(List<MessageBean> data);

        void onFauild(String msg);
    }

    public interface MessageModel extends IModel {
        void onGetMessageModel(Map<String, String> map, BaseSubscriber<List<MessageBean>> subscriber);
    }

    public interface MessagePresent extends IPresenter<MessageView> {
        void onGetMessagePresent(Map<String, String> map);
    }
}
