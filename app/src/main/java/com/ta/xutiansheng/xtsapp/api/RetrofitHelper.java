package com.ta.xutiansheng.xtsapp.api;


import com.ta.xutiansheng.xtsapp.api.interceptor.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author xutiansheng
 */
public class RetrofitHelper {

    private static RetrofitHelper sRetrofitHelper;
    private OkHttpClient mOkHttpClient;

    private RetrofitHelper() {
        initOkHttpClient();
    }

    public static RetrofitHelper getInstance() {
        if (sRetrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (sRetrofitHelper == null) {
                    sRetrofitHelper = new RetrofitHelper();
                }
            }
        }
        return sRetrofitHelper;
    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> com.ta.xutiansheng.xtsapp.api.Timber.tag("Retrofit").e(message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
//                    File cacheFile = new File(FltApplication.getInstance().getCacheDir(), "HttpCache");
//                    //100Mb
//                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(false)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
//                            .cache(cache)
                            .addInterceptor(new HeaderInterceptor())
                            .addInterceptor(logging)
                            .build();
                }
            }
        }
    }

    public com.ta.xutiansheng.xtsapp.api.IApiService createApiService(String baseUrl) {
        return getRetrofit(baseUrl).create(com.ta.xutiansheng.xtsapp.api.IApiService.class);
    }

    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


}
