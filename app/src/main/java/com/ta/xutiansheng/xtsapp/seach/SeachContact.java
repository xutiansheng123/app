package com.ta.xutiansheng.xtsapp.seach;

import com.ta.xutiansheng.xtsapp.api.rx.BaseSubscriber;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.mvp.model.IModel;
import com.ta.xutiansheng.xtsapp.mvp.presenter.IPresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import java.util.List;
import java.util.Map;

public class SeachContact {
    public interface SeachView extends IView {
        void SeachForKeyWordSuccess(List<ShopBean> list);
        void onFauiler(String msg);
    }

    public interface SeachModel extends IModel {
        void  SeachForKeyWordModel(Map<String ,Object> map, BaseSubscriber<List<ShopBean>> subscriber);
    }

    public interface SeachPresent extends IPresenter<SeachView> {
        void SeachForKeyWordPresent(Map<String,Object> map);
    }
}
