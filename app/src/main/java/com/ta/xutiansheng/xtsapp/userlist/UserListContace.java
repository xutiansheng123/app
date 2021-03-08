package com.ta.xutiansheng.xtsapp.userlist;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.List;
import java.util.Map;

import rx.Subscriber;

public class UserListContace {
    public interface getUserListView extends IView {
        void onGetListSuccess(List<UserBean> list);

        void onGetFauild(String msg);
    }

    public interface getUserListModel extends IModel {
        void onGetListModel(Map<String, String> map, BaseSubscriber<List<UserBean>> subscriber);
    }

    public interface getUserListPresent extends IPresenter<getUserListView> {
        void onGetUserList(Map<String, String> map);
    }
}
