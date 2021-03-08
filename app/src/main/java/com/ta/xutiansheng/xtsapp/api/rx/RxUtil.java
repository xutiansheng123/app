package com.ta.xutiansheng.xtsapp.api.rx;


import android.os.Looper;
import android.util.Log;

import com.ta.xutiansheng.xtsapp.api.bean.BaseResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * @author xutiansheng
 */
public class RxUtil {
    /**
     * 统一线程处理
     *
     * @param <T> 统一线程处理
     * @return Observable.Transformer
     */
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.getMainLooper()));
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return Observable.Transformer
     */
    public static <T> Observable.Transformer<BaseResponse<T>, T> handleResult() {
        return baseResponseObservable -> baseResponseObservable.flatMap((Func1<BaseResponse<T>, Observable<T>>) baseResponse -> {
            Log.d(TAG, "handleResult: "+baseResponse.toString());
            if (baseResponse.getCode() ==200) {
                return createObservable(baseResponse.getResult());
            } else {
                return Observable.error(new OtherException(baseResponse.getCode()+"", baseResponse.getMsg()));
            }

        });
    }
 /**
     * 统一返回结果处理
     * @param <T> 指定的泛型类型
     * @return Observable.Transformer
     */
    public static <T> Observable.Transformer<BaseResponse<T>, T> handleResult2() {
        return baseResponseObservable -> baseResponseObservable.flatMap((Func1<BaseResponse<T>, Observable<T>>) baseResponse -> {
            if (baseResponse.getCode() == 200) {
                return createObservable((T) baseResponse.getMsg());
            } else {
                return Observable.error(new OtherException(baseResponse.getCode()+"", baseResponse.getMsg()));
            }

        });
    }

    /**
     * 得到 Observable
     *
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createObservable(final T t) {
        return Observable.unsafeCreate(subscriber -> {
            try {
                subscriber.onNext(t);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

}
