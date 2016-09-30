package com.sz.communityservice.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.communityservice.R;

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
        mActivity = this;
        initView();
        setchenjin();
        initData();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected void initTitle(String title) {
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

    /**
     * 设置为4.4的沉浸式状态栏
     *
     */
    private void setchenjin() {
        FrameLayout root = (FrameLayout) findViewById(R.id.fl_title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        // activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (root != null) {
                root.setPadding(0, getStatusBarHeight(this), 0, 0);
            }
        }else {
            //如果是Android4.4以下的话，设置他的高度和title一样
            RelativeLayout title = (RelativeLayout) findViewById(R.id.rl_title);
            root.getLayoutParams().height = title.getLayoutParams().height;

        }
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    /**
     * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
     *
     * @return 返回状态栏高度的像素值。
     */
    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
