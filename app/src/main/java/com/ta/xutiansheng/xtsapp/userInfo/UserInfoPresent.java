package com.ta.xutiansheng.xtsapp.userInfo;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.Map;

public class UserInfoPresent extends BasePresenter<UserInfoContact.UserInfoView, UserInfoContact.UserInfoModel> implements UserInfoContact.UserInfoPresent {
    @Override
    protected UserInfoContact.UserInfoModel createModule() {
        return new UserInfoModel();
    }

    @Override
    public void getUserInfo(Map<String, Object> map) {
        mModel.getUserInfo(map, new BaseSubscriber<UserBean>() {
            @Override
            public void onSuccess(UserBean data) {
                getView().getuserInfo(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().getuserFaluid(msg);
            }

            @Override
            public void onError() {
                getView().getuserFaluid(ResultType.EerrorCMsg);

            }
        });
    }

    @Override
    public void updateUserInfo(Map<String, Object> map) {
        mModel.updateUserInfo(map, new BaseSubscriber<Object>() {
            @Override
            public void onSuccess(Object data) {
                getView().updateUserInfo((String) data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().updateUserInfoFauild(msg);
            }

            @Override
            public void onError() {
                getView().updateUserInfoFauild(ResultType.EerrorCMsg);
            }
        });
    }
}
