package com.sz.communityservice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sz.communityservice.R;
import com.sz.communityservice.utils.HttpUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        HttpUtils.postfile();



    }

}

