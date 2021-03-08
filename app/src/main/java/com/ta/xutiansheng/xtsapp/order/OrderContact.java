package com.ta.xutiansheng.xtsapp.order;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.List;
import java.util.Map;

public class OrderContact {
    public interface OrderView extends IView {
        void getOrderList(List<OrderBean> list);

        void getOrderFaulier(String msg);
    }

    public interface OrderModel extends IModel {
        void getOrderList(Map<String, Object> map, BaseSubscriber<List<OrderBean>> subscriber);
    }

    public interface OrderPresent extends IPresenter<OrderView> {
        void getOrderList(Map<String, Object> map);
    }
}
