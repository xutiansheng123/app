package com.ta.xutiansheng.xtsapp.login;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.bean.LoginResult;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.Map;

public class LoginPrensenter extends BasePresenter<LoginCont.LoginView, LoginCont.LoginModel> implements LoginCont.LoginPrensenter {
    @Override
    public void onLoging(Map<String, String> map) {
         mModel.onLogin(map, new BaseSubscriber<LoginResult>() {
             @Override
             public void onSuccess(LoginResult data) {
                 getView().onLoginSuccess(data);
             }

             @Override
             public void onFailed(Throwable ex, String code, String msg) {
                 getView().onLoginFaiure(msg);
             }

             @Override
             public void onError() {
                 getView().onLoginFaiure(ResultType.EerrorCMsg);
             }
         });
    }

    @Override
    protected LoginCont.LoginModel createModule() {
        return new LoginModel();
    }
}
