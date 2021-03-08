package com.ta.xutiansheng.xtsapp.address;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

public class AddressContact {
    public interface AddressView extends IView {
        void onCreateAddressSuccess(String msg);

        void onDeleteAddressSuccess(String msg);

        void onGetAddressList(List<AddressResult> list);

        void onFaulire(String msg);

        void getSeachAddressList(List<BaiduResult> list);
    }

    public interface AddressModel extends IModel {
        void onCreateAddress(Map<String, Object> map, BaseSubscriber<Object> baseSubscriber);

        void onDeleteAddressSuccess(Map<String, Object> map, BaseSubscriber<Object> baseSubscriber);

        void onGetAddressList(Map<String, Object> map, BaseSubscriber<List<AddressResult>> subscriber);

        void getSeachAddressList(Map<String, Object> map, BaseSubscriber<List<BaiduResult>> subscriber);
    }

    public interface AddressPresent extends IPresenter<AddressView> {
        void onCreateAddress(Map<String, Object> map);

        void onDeleteAddressSuccess(Map<String, Object> map);

        void onGetAddressList(Map<String, Object> map);

        void getSeachAddressList(Map<String, Object> map);

    }
}
