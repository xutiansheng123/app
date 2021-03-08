package com.ta.xutiansheng.xtsapp.index;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.IpLocationResult;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.List;
import java.util.Map;

public class IndexPreseneter extends BasePresenter<IndexContact.IndexView, IndexContact.IndexModel> implements IndexContact.IndexPersenter {
    @Override
    protected IndexContact.IndexModel createModule() {
        return new IndexModel();
    }

    @Override
    public void GetShopList(Map<String, String> map) {
        mModel.GetShopList(new BaseSubscriber<List<ShopBean>>() {
            @Override
            public void onSuccess(List<ShopBean> data) {
                getView().onShopListCallBack(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onShopListFauile(msg);
            }

            @Override
            public void onError() {
                getView().onShopListFauile(ResultType.EerrorCMsg);
            }
        }, map);
    }

    @Override
    public void GetIpLcationPresent() {
        mModel.GetIpLocation(new BaseSubscriber<IpLocationResult>() {
            @Override
            public void onSuccess(IpLocationResult data) {
                getView().onIpLocation(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onShopListFauile(msg);
            }

            @Override
            public void onError() {
                getView().onShopListFauile(ResultType.EerrorCMsg);
            }
        });
    }
}
