package com.sz.communityservice.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sz.communityservice.R;
import com.sz.communityservice.application.MyApplication;
import com.sz.communityservice.base.BaseActivity;
import com.sz.communityservice.bean.CmdEnum;
import com.sz.communityservice.bean.LoginBean;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.immanage.ChatClient;
import com.sz.communityservice.immanage.MsgManage;
import com.sz.communityservice.utils.SharePreferencesUtils;
import com.sz.communityservice.utils.Utils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private AutoCompleteTextView email;
    private EditText etpassword;
    private TextView emailSignInButton,register_in_button;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        initTitle("登录");
        email = (AutoCompleteTextView) findViewById(R.id.email);
        etpassword = (EditText) findViewById(R.id.password);
        emailSignInButton = (TextView) findViewById(R.id.email_sign_in_button);
        register_in_button = (TextView) findViewById(R.id.register_in_button);
    }

    @Override
    protected void initListener() {
        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatClient.getIntance(LoginActivity.this).channel.isOpen();
                Utils.printLog("点击登录");
                String username = email.getText().toString();
                String password = etpassword.getText().toString();
                ChatClient.getIntance(LoginActivity.this).sendMessage(MsgManage.createLoginMsg(username,password));
            }
        });

        register_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,RegisterActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        email.setText(SharePreferencesUtils.getUsername(this));
    }

    public void onEventMainThread(MsgObject msg) {
        if (CmdEnum.LOGIN.getCmd()==msg.getC()){
            //登录
            LoginBean loginBean = new Gson().fromJson(msg.getM(),LoginBean.class);
            if (loginBean.flag.equals("true")){
                //登录成功
                MyApplication.mGlobalValue.userid = email.getText().toString();
                SharePreferencesUtils.setUsername(this,email.getText().toString());
                Intent intent = new Intent(this,ChatListActivity.class);
                startActivity(intent);
            }
            Utils.showToast(this,loginBean.msg);
        }

    }

}

