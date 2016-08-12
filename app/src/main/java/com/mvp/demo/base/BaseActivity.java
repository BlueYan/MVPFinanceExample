package com.mvp.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 创建人：
 * 创建时间： 2016/8/9 11
 * 功能概述: 如果在有用到fragment，要在Activity的OnCreate()方法中去判断saveInstanceState是否为空，加载根Fragment
 *          if( saveInstanceState == null ) {
 *              加载根Fragment
 *          }
 * 修改人：
 * 修改时间：
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

//    protected IBasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    protected abstract int getLayoutId();

//    protected abstract void setPresenter();

    protected abstract void initView();

    protected abstract void initEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.onDestory();
    }
}
