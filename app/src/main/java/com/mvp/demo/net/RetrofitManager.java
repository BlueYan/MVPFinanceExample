package com.mvp.demo.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 创建人：
 * 创建时间： 2016/8/9 14
 * 功能概述: Retrofit管理器
 * 修改人：
 * 修改时间：
 */
public class RetrofitManager {

    public static Retrofit mRetrofit = null;

    public static Retrofit init() {
        if ( mRetrofit == null ) {
            OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
            mBuilder.addInterceptor(headerInterceptor);  //设置请求头
            mBuilder.connectTimeout(15, TimeUnit.SECONDS); //设置请求连接时间
            mBuilder.retryOnConnectionFailure(true); //设置错误重连
            OkHttpClient mClient = mBuilder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(NetConstant.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                    .client(mClient)
                    .build();
        }
        return mRetrofit;
    }


    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("apikey", NetConstant.API_KEY);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };
}
