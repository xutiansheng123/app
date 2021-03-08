package com.ta.xutiansheng.xtsapp.api.interfaces;

/**
 * @author xutiansheng
 * 网络请求回调
 */
public interface CallBackListener<T> {
    void onSuccess(T data);

    void onFailed(Throwable ex, String code, String msg);

    void onError();
}
