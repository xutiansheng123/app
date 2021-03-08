package com.ta.xutiansheng.xtsapp.address;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.List;
import java.util.Map;

public class AddressPresent extends BasePresenter<AddressContact.AddressView, AddressContact.AddressModel> implements AddressContact.AddressPresent {
    @Override
    public void onCreateAddress(Map<String, Object> map) {
        mModel.onCreateAddress(map, new BaseSubscriber<Object>() {
            @Override
            public void onSuccess(Object data) {
                getView().onCreateAddressSuccess("创建成功");
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onFaulire(msg);
            }

            @Override
            public void onError() {
                getView().onFaulire(ResultType.EerrorCMsg);
            }
        });
    }

    @Override
    public void onDeleteAddressSuccess(Map<String, Object> map) {
        mModel.onDeleteAddressSuccess(map, new BaseSubscriber<Object>() {
            @Override
            public void onSuccess(Object data) {
                getView().onDeleteAddressSuccess("删除成功");
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onDeleteAddressSuccess(msg);
            }

            @Override
            public void onError() {
                getView().onDeleteAddressSuccess(ResultType.EerrorCMsg);
            }
        });
    }

    @Override
    public void onGetAddressList(Map<String, Object> map) {
        mModel.onGetAddressList(map, new BaseSubscriber<List<AddressResult>>() {
            @Override
            public void onSuccess(List<AddressResult> data) {
                getView().onGetAddressList(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onFaulire(msg);
            }

            @Override
            public void onError() {
                getView().onFaulire(ResultType.EerrorCMsg);
            }
        });
    }

    @Override
    public void getSeachAddressList(Map<String, Object> map) {
        mModel.getSeachAddressList(map, new BaseSubscriber<List<BaiduResult>>() {
            @Override
            public void onSuccess(List<BaiduResult> data) {
                getView().getSeachAddressList(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onFaulire(msg);
            }

            @Override
            public void onError() {
                getView().onFaulire(ResultType.EerrorCMsg);
            }
        });
    }

    @Override
    protected AddressContact.AddressModel createModule() {
        return new AddressModel();
    }
}
