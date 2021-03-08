package com.ta.xutiansheng.xtsapp.userInfo;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.Map;

public class UserInfoContact {
    public interface UserInfoView extends IView {
        void getuserInfo(UserBean data);

        void getuserFaluid(String msg);

        void updateUserInfo(String msg);

        void updateUserInfoFauild(String msg);
    }

    public interface UserInfoModel extends IModel {
        void getUserInfo(Map<String, Object> map, BaseSubscriber<UserBean> subscriber);

        void updateUserInfo(Map<String, Object> map, BaseSubscriber<Object> subscriber);
    }

    public interface UserInfoPresent extends IPresenter<UserInfoView> {
        void getUserInfo(Map<String, Object> map);

        void updateUserInfo(Map<String, Object> map);
    }
}
