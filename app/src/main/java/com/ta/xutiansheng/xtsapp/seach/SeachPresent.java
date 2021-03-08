package com.ta.xutiansheng.xtsapp.seach;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.List;
import java.util.Map;

public class SeachPresent extends BasePresenter<SeachContact.SeachView, SeachContact.SeachModel> implements SeachContact.SeachPresent {
    @Override
    protected SeachContact.SeachModel createModule() {
        return new SeachModel();
    }

    @Override
    public void SeachForKeyWordPresent(Map<String, Object> map) {
        mModel.SeachForKeyWordModel(map, new BaseSubscriber<List<ShopBean>>() {
            @Override
            public void onSuccess(List<ShopBean> data) {
                getView().SeachForKeyWordSuccess(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onFauiler(msg);
            }

            @Override
            public void onError() {
                getView().onFauiler(ResultType.EerrorCMsg);
            }
        });
    }
}
