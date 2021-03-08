package com.ta.xutiansheng.xtsapp.order;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.List;
import java.util.Map;

public class OrderPresent extends BasePresenter<OrderContact.OrderView, OrderContact.OrderModel> implements OrderContact.OrderPresent {
    @Override
    protected OrderContact.OrderModel createModule() {
        return new OrderModel();
    }

    @Override
    public void getOrderList(Map<String, Object> map) {
        mModel.getOrderList(map, new BaseSubscriber<List<OrderBean>>() {
            @Override
            public void onSuccess(List<OrderBean> data) {
                getView().getOrderList(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().getOrderFaulier(msg);
            }

            @Override
            public void onError() {
                getView().getOrderFaulier(ResultType.EerrorCMsg);

            }
        });
    }
}
