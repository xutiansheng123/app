package com.ta.xutiansheng.xtsapp.userlist;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.List;
import java.util.Map;

public class UserListPresent extends BasePresenter<UserListContace.getUserListView, UserListContace.getUserListModel> implements UserListContace.getUserListPresent {
    @Override
    protected UserListContace.getUserListModel createModule() {
        return new UserListModel();
    }

    @Override
    public void onGetUserList(Map<String, String> map) {
        mModel.onGetListModel(map, new BaseSubscriber<List<UserBean>>() {
            @Override
            public void onSuccess(List<UserBean> data) {
                getView().onGetListSuccess(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onGetFauild(msg);
            }

            @Override
            public void onError() {
                getView().onGetFauild(ResultType.EerrorCMsg);
            }
        });
    }
}
