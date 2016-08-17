package com.sz.communityservice.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by xingyun on 2016/8/9.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        initView();
        initListener();
        initData();
    }
    protected abstract View initView() ;
    protected abstract void initListener() ;
    protected abstract void initData() ;
}
