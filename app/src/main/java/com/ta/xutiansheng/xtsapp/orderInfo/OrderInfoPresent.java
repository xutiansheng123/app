package com.ta.xutiansheng.xtsapp.orderInfo;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.Map;

public class OrderInfoPresent extends BasePresenter<OrderInfoContact.OrderInfoView, OrderInfoContact.OrderInfoModel> implements OrderInfoContact.OrderInfoPresenet {
    @Override
    protected OrderInfoContact.OrderInfoModel createModule() {
        return new OrderInfoModel();
    }
    @Override
    public void getOrderInfo(Map<String, Object> map) {
        mModel.getOrderInfo(map, new BaseSubscriber<OrderBean>() {
            @Override
            public void onSuccess(OrderBean data) {
                getView().getOrderInfo(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().getOrderInfoFauilre(msg);
            }

            @Override
            public void onError() {
                getView().getOrderInfoFauilre(ResultType.EerrorCMsg);
            }
        });
    }
    @Override
    public void upDateOrderState(Map<String, Object> map) {
        mModel.upDateOrderState(map, new BaseSubscriber<String>() {
            @Override
            public void onSuccess(String data) {
                getView().uPdateOrdersate(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().uPdateOrdersate(msg);
            }

            @Override
            public void onError() {
                getView().uPdateOrdersate(ResultType.EerrorCMsg);

            }
        });
    }
}
