package com.ta.xutiansheng.xtsapp.createOrder;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.Map;

public class CreateOrderContact  {
    public interface CreateOrderView extends IView {
        void createOrderSuccess(String data);
        void createOrderFauile(String msg);
        void getAddressId(AddressResult bean);
    }
    public interface CreateOrderModel extends IModel{
        void createOrderModel(Map<String,Object> map, BaseSubscriber<String> subscriber);
        void getAddressId(Map<String,Object> map,BaseSubscriber<AddressResult> subscriber);

    }
    public interface CreateOrderPresent extends IPresenter<CreateOrderView>{
        void createOrderModel(Map<String,Object> map);
        void getAddressId(Map<String,Object> map);
    }
}
