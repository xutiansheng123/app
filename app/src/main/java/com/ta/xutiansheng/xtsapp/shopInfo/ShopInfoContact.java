package com.ta.xutiansheng.xtsapp.shopInfo;

import com.ta.xutiansheng.xtsapp.api.bean.BaseResponse;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.List;
import java.util.Map;

import rx.Subscriber;

public class ShopInfoContact {
    public interface ShopInfoView extends IView {
        //获取菜单
        void ongetShopMenu(List<ShopMenu> data);

        //获取商家详情　
        void ongetShopInfo(ShopBean bean);
        //请求异常
        void onFauiled(String msg);
    }

    public interface ShopModel extends IModel {
        void ongetShopMenu(String shopId, Subscriber<List<ShopMenu>> subscriber);
        //TODO 这里添加获取商家详情
        void getShopInfo(Map<String,String> map, Subscriber<ShopBean> subscriber);
    }

    public interface ShopPresneter extends IPresenter<ShopInfoView> {
        void ongetShopMenuForShopid(String shopId);
        void getShopInfo(Map<String,String> map);

    }
}
