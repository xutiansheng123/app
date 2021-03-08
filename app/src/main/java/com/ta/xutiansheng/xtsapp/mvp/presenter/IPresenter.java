package com.ta.xutiansheng.xtsapp.mvp.presenter;


import com.ta.xutiansheng.xtsapp.mvp.view.IView;

/**
 * @author xutiansheng
 */
public interface IPresenter<T extends IView> {
    /**
     * 绑定view
     * @param view view
     */
    void attachView(T view);

    /**
     * 分离view
     */
    void detachView();

    /**
     * 判断view是否已经销毁
     * @return true 未销毁
     */
    boolean isViewAttach();

}
