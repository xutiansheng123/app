package com.ta.xutiansheng.xtsapp.shopInfo;

import com.ta.xutiansheng.xtsapp.api.ResultType;
import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import java.util.List;
import java.util.Map;

public class ShopPresenter extends BasePresenter<ShopInfoContact.ShopInfoView, ShopInfoContact.ShopModel> implements ShopInfoContact.ShopPresneter {
    @Override
    protected ShopInfoContact.ShopModel createModule() {
        return new ShopMenuModel();
    }

    @Override
    public void ongetShopMenuForShopid(String shopId) {
        mModel.ongetShopMenu(shopId, new BaseSubscriber<List<ShopMenu>>() {
            @Override
            public void onSuccess(List<ShopMenu> data) {
                getView().ongetShopMenu(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onFauiled(msg);
            }

            @Override
            public void onError() {
                getView().onFauiled(ResultType.EerrorCMsg);
            }
        });
    }

    @Override
    public void getShopInfo(Map<String, String> map) {
        mModel.getShopInfo(map, new BaseSubscriber<ShopBean>() {
            @Override
            public void onSuccess(ShopBean data) {
                getView().ongetShopInfo(data);
            }

            @Override
            public void onFailed(Throwable ex, String code, String msg) {
                getView().onFauiled(msg);
            }

            @Override
            public void onError() {
                getView().onFauiled(ResultType.EerrorCMsg);

            }
        });
    }
}
