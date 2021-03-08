package com.ta.xutiansheng.xtsapp.mvp.model;

import rx.Subscription;

/**
 * @author xutiansheng
 */
public interface IModel {
    void unSubscribe();

    void addSubscribe(Subscription subscription);
}
