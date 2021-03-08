package com.ta.xutiansheng.xtsapp.createOrder;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.Map;

public class CreateOrderPresent extends BasePresenter<CreateOrderContact.CreateOrderView, CreateOrderContact.CreateOrderModel> implements CreateOrderContact.CreateOrderPresent {
    @Override
    public void createOrderModel(Map<String, Object> map) {
        mModel.createOrderModel(map, new BaseSubscriber<String>() {
            @Override
            public void onSuccess(String data) {
                getView().createOrderSuccess(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().createOrderFauile(msg);
            }

            @Override
            public void onError() {
                getView().createOrderFauile(ResultType.EerrorCMsg);
            }
        });
    }

    @Override
    public void getAddressId(Map<String, Object> map) {
           mModel.getAddressId(map, new BaseSubscriber<AddressResult>() {
               @Override
               public void onSuccess(AddressResult data) {
                   getView().getAddressId(data);
               }

               @Override
               public void onFailed(Throwable ex, String code, String msg) {
                   getView().createOrderFauile(msg);
               }

               @Override
               public void onError() {
                   getView().createOrderFauile(ResultType.EerrorCMsg);
               }
           });
    }

    @Override
    protected CreateOrderContact.CreateOrderModel createModule() {
        return new CreateOrderModel();
    }
}
