package com.ta.xutiansheng.xtsapp.api.rx;

import android.util.Log;

import com.ta.xutiansheng.xtsapp.api.HttpConstants;
import com.ta.xutiansheng.xtsapp.api.Timber;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * @author xutiansheng
 * 自定义 观察者Subscriber，接口返回做自定义处理
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private static final String TAG = "BaseSubscriber";

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onNext(T t) {
            onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        Timber.tag(TAG).e(e);
        onCompleted();
        onFail(e);
    }

    /**
     * 失败
     */
    private void onFail(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            int code = 0;
            String errorBody = null;
            try {
                code = ((HttpException) e).code();
                errorBody = ((HttpException) e).response().errorBody().string();
            } catch (Exception ex) {
//                ex.printStackTrace();
            } finally {
                if (errorBody == null) {
                    errorBody = "";
                }
            }
            onFailed(e, code + "", errorBody);
        } else if (e instanceof com.ta.xutiansheng.xtsapp.api.rx.OtherException) {
            com.ta.xutiansheng.xtsapp.api.rx.OtherException exception = (com.ta.xutiansheng.xtsapp.api.rx.OtherException) e;
            parseCode(exception, exception.getCode(), exception.getMsg());
        } else {
            onError();
        }
    }

    /**
     * 解析code
     */
    private void parseCode(Throwable ex, String code, String desc) {
        switch (code) {
            case HttpConstants.CODE_FORCE_LOGOUT:
                // 账号在其他设备登录
                com.ta.xutiansheng.xtsapp.api.rx.RxBus.getInstance().post(new com.ta.xutiansheng.xtsapp.api.rx.ExceptionEvent(HttpConstants.CODE_FORCE_LOGOUT, desc));
                break;
            case HttpConstants.CODE_TOKEN_INVALID:
                // token过期
                com.ta.xutiansheng.xtsapp.api.rx.RxBus.getInstance().post(new com.ta.xutiansheng.xtsapp.api.rx.ExceptionEvent(HttpConstants.CODE_TOKEN_INVALID, desc));
                break;
            default:
                onFailed(ex, code, desc);
                break;
        }
    }

    public abstract void onSuccess(T data);

    public abstract void onFailed(Throwable ex, String code, String msg);

    public abstract void onError();

}
