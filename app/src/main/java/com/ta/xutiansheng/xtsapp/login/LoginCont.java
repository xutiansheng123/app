package com.ta.xutiansheng.xtsapp.login;

import com.ta.xutiansheng.xtsapp.api.bean.BaseResponse;
import com.ta.xutiansheng.xtsapp.api.bean.LoginResult;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.Map;

import rx.Subscriber;
import rx.observers.Subscribers;

public class LoginCont {
    public interface LoginView extends IView {
        void onLoginSuccess(LoginResult result);

        void onLoginFaiure(String  result);
    }

    public interface LoginModel extends IModel {
        void onLogin(Map<String, String> map, Subscriber<LoginResult> subscriber);
    }

    public interface LoginPrensenter extends IPresenter<LoginView> {
        void onLoging(Map<String, String> map);
    }
}
