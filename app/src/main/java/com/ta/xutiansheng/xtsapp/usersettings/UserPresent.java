package com.ta.xutiansheng.xtsapp.usersettings;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.io.File;
import java.util.List;
import java.util.Map;

public class UserPresent extends BasePresenter<UserSettingsContact.UserSettingView, UserSettingsContact.UserSettingModel> implements UserSettingsContact.UserSettingPresent {
    @Override
    protected UserSettingsContact.UserSettingModel createModule() {
        return new UserSettingModel();
    }

    @Override
    public void onUploadHeadModel(List<File> files, Map<String, Object> map) {
       mModel.onUploadHeadModel(files, map, new BaseSubscriber<List<String>>() {
           @Override
           public void onSuccess(List<String> data) {
               getView().onUploadHeadResult(data);
           }

           @Override
           public void onFailed(Throwable ex, String code, String msg) {
               getView().onFulide(msg);
           }

           @Override
           public void onError() {
               getView().onFulide(ResultType.EerrorCMsg);
           }
       });
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
}
