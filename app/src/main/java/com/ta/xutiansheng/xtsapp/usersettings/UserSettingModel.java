package com.ta.xutiansheng.xtsapp.usersettings;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.api.rx.RxUtil;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.model.BaseModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;

public class UserSettingModel extends BaseModel implements UserSettingsContact.UserSettingModel {


    @Override
    public void onUploadHeadModel(List<File> files, Map<String, Object> map, BaseSubscriber<List<String>> subscriber) {
        MultipartBody.Builder build = new MultipartBody.Builder();
        for (int i = 0; i < files.size(); i++) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), files.get(i));
            build.addFormDataPart("image", files.get(i).getName(), requestBody);
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html;charset=utf-8"), (String) map.get("userName"));
        Map<String,RequestBody> requestBodyMap =new HashMap<>();
        requestBodyMap.put("userName",requestBody);
        Subscription subscribe = mApi.upLoadHeadImg(build.build().parts(),requestBodyMap).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }

    @Override
    public void getUserInfo(Map<String, Object> map, BaseSubscriber<UserBean> subscriber) {
        Subscription subscribe = mApi.getuserInfo(map).compose(RxUtil.applySchedulers()).compose(RxUtil.handleResult()).subscribe(subscriber);
        addSubscribe(subscribe);
    }
}
