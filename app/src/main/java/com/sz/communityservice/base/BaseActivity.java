package com.sz.communityservice.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.communityservice.R;

import de.greenrobot.event.EventBus;

/**
 * Created by xingyun on 2016/8/9.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mActivity;
    protected ImageView iv_back;
    protected TextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventbus
        EventBus.getDefault().register(this);
        mActivity = this;
        initView();
        initListener();
        initData();
    }

    protected abstract void initView() ;
    protected abstract void initListener() ;
    protected abstract void initData() ;

    protected void initTitle(String title){
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title.setText(title);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
