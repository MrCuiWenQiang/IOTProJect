package com.iot.manager.setting.okhttp;

import android.text.TextUtils;


import com.iot.manager.model.LoginModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Function : 为请求添加token请求头
 * Remarks  :
 * Created by Mr.C on 2019/1/11 0011.
 */
public class TokenHeaderInterceptor implements Interceptor {

    private LoginModel loginModel = new LoginModel();

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = loginModel.findTeleByToken();
        if (TextUtils.isEmpty(token)) {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest);
        } else {
            Request originalRequest = chain.request();
            Request updateRequest = originalRequest.newBuilder().header("token", token).build();
            return chain.proceed(updateRequest);
        }
    }
}
