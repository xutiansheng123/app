package com.ta.xutiansheng.xtsapp.usersettings;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

public class UserSettingsContact {
    public interface UserSettingView extends IView {
        void onUploadHeadResult(List<String> urls);

        void onFulide(String msg);

        void getuserInfo(UserBean data);

        void getuserFaluid(String msg);
    }

    public interface UserSettingModel extends IModel {
        void onUploadHeadModel(List<File> files, Map<String,Object> map, BaseSubscriber<List<String>> subscriber);
        void getUserInfo(Map<String, Object> map, BaseSubscriber<UserBean> subscriber);

    }

    public interface UserSettingPresent extends IPresenter<UserSettingView> {
        void onUploadHeadModel(List<File> files,Map<String,Object> map);
        void getUserInfo(Map<String, Object> map);
    }
}
