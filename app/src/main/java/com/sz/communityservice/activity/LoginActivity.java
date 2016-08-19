package com.sz.communityservice.activity;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sz.communityservice.R;
import com.sz.communityservice.base.BaseActivity;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.immanage.ChatClient;
import com.sz.communityservice.immanage.MsgManage;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private AutoCompleteTextView email;
    private EditText password;
    private Button emailSignInButton;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    }

    @Override
    protected void initListener() {
        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatClient.getIntance(LoginActivity.this).channel.isOpen();
                ChatClient.getIntance(LoginActivity.this).sendMessage(MsgManage.createLoginMsg("jack","666666"));
            }
        });
    }

    @Override
    protected void initData() {

    }

    public void onEventMainThread(MsgObject msg) {
        Toast.makeText(this, msg.getM(), Toast.LENGTH_SHORT).show();
    }

}

