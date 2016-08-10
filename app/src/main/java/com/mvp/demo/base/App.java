package com.mvp.demo.base;

import android.app.Application;

import com.mvp.demo.net.RetrofitManager;
import com.mvp.library.utils.LogUtils;

/**
 * 创建人：
 * 创建时间： 2016/8/9 16
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(true); //初始化
        RetrofitManager.init();
    }
}
