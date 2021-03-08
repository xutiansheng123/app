package com.ta.xutiansheng.xtsapp.orderInfo;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.Map;

public class OrderInfoContact {
    public interface OrderInfoView extends IView {
        void getOrderInfo(OrderBean orderBean);

        void getOrderInfoFauilre(String msg);

        void uPdateOrdersate(String msg);
    }

    public interface OrderInfoModel extends IModel {
        void getOrderInfo(Map<String, Object> map, BaseSubscriber<OrderBean> subscriber);

        void upDateOrderState(Map<String, Object> map, BaseSubscriber<String> subscriber);
    }

    public interface OrderInfoPresenet extends IPresenter<OrderInfoView> {
        void getOrderInfo(Map<String, Object> map);

        void upDateOrderState(Map<String, Object> map);
    }
}
