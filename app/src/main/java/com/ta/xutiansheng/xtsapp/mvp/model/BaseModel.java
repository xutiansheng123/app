package com.ta.xutiansheng.xtsapp.mvp.model;



import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.IApiService;
import com.ta.xutiansheng.xtsapp.api.RetrofitHelper;
import com.ta.xutiansheng.xtsapp.mvp.AppConstants;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author xutiansheng
 */
public class BaseModel implements IModel {
    protected IApiService mApi;
    private CompositeSubscription mCompositeSubscription;

    public BaseModel() {
        this.mApi = RetrofitHelper.getInstance().createApiService(AppConstants.BASE_SERVER_IP);
    }

    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.clear();
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
    protected RequestBody createRequestBody(String json){
         return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
}
