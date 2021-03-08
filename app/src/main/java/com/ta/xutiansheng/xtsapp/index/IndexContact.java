package com.ta.xutiansheng.xtsapp.index;

import com.ta.xutiansheng.xtsapp.bean.IpLocationResult;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.List;
import java.util.Map;

import rx.Subscriber;

public class IndexContact {
    public interface IndexView extends IView{
        void onShopListCallBack(List<ShopBean> list);
        void onShopListFauile(String msg);
        void onIpLocation(IpLocationResult result);
    }
    public interface IndexModel extends IModel{
        void GetShopList(Subscriber<List<ShopBean>> subscriber, Map<String,String> map);
        void GetIpLocation(Subscriber<IpLocationResult> subscriber);
    }
    public interface IndexPersenter extends IPresenter<IndexView> {
        void GetShopList(Map<String,String> map);
        void GetIpLcationPresent();
    }
}
