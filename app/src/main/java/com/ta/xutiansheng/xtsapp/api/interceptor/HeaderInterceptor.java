package com.ta.xutiansheng.xtsapp.api.interceptor;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author xutiansheng
 * OKHttpClient请求头
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        // TODO
        if (GlobleSettingData.getInstance().getAuthInfo() != null) {
            String accessToken = GlobleSettingData.getInstance().getAuthInfo().getToken();
            builder.addHeader("Content-Type", "application/json");
            if (!TextUtils.isEmpty(accessToken)) {
                builder.addHeader("x-ac-token-ticket", accessToken);
                builder.addHeader("loginType", "0");
            }
            builder.addHeader("cookie","JSESSIONID="+GlobleSettingData.getInstance().getAuthInfo().getSessionId());
        }
        return chain.proceed(builder.build());
    }
}
