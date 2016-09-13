package com.sz.communityservice.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sz.communityservice.R;
import com.sz.communityservice.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private EditText etPhone;
    private EditText etCode;
    private TextView tvGetcode;
    private TextView tvCommit;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);

        initTitle("注册");
        etPhone = (EditText) findViewById(R.id.et_phone);
        etCode = (EditText) findViewById(R.id.et_code);
        tvGetcode = (TextView) findViewById(R.id.tv_getcode);
        tvCommit = (TextView) findViewById(R.id.tv_commit);

    }

    @Override
    protected void initListener() {
        tvGetcode.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcode:
                //获取验证码
                break;
            case R.id.tv_commit:
                //注册
                break;
        }
    }
}
