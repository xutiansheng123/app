package com.ta.xutiansheng.xtsapp.api.interceptor;

import com.ta.xutiansheng.xtsapp.api.bean.LoginResult;

public class GlobleSettingData {
    private static GlobleSettingData instance;

    public static GlobleSettingData getInstance() {
        if (instance == null) {
            synchronized (GlobleSettingData.class) {
                if (instance == null) {
                    instance = new GlobleSettingData();
                }
            }
        }
        return instance;
    }

    private LoginResult result;

    public LoginResult getAuthInfo() {
        return result;
    }

    public void setResult(LoginResult result) {
        this.result = result;
    }
}
