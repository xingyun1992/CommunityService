package com.sz.communityservice.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.sz.communityservice.R;
import com.sz.communityservice.application.MyApplication;
import com.sz.communityservice.base.BaseActivity;
import com.sz.communityservice.bean.CmdEnum;
import com.sz.communityservice.bean.LoginBean;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.immanage.ChatClient;
import com.sz.communityservice.immanage.MsgManage;
import com.sz.communityservice.utils.Utils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private AutoCompleteTextView email;
    private EditText etpassword;
    private Button emailSignInButton;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        etpassword = (EditText) findViewById(R.id.password);
        emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
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
    }

    @Override
    protected void initData() {

    }

    public void onEventMainThread(MsgObject msg) {
//        Toast.makeText(this, msg.getM(), Toast.LENGTH_SHORT).show();
        if (CmdEnum.LOGIN.getCmd()==msg.getC()){
            //登录
            LoginBean loginBean = new Gson().fromJson(msg.getM(),LoginBean.class);
            if (loginBean.flag.equals("true")){
                //登录成功
                MyApplication.mGlobalValue.userid = email.getText().toString();
                Intent intent = new Intent(this,ChatListActivity.class);
                startActivity(intent);
            }
            Utils.showToast(this,loginBean.msg);
        }


    }

}

