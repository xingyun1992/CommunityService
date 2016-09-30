package com.sz.communityservice.activity.user;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sz.communityservice.R;
import com.sz.communityservice.base.BaseActivity;
import com.sz.communityservice.bean.CmdEnum;
import com.sz.communityservice.bean.LoginBean;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.immanage.ChatClient;
import com.sz.communityservice.immanage.MsgManage;
import com.sz.communityservice.utils.Utils;

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
                ChatClient.getIntance(this).sendMessage(MsgManage.createGetCodeMsg(etPhone.getText().toString()));
                break;
            case R.id.tv_commit:
                //注册
                String msg = MsgManage.createRegisterMsg(etPhone.getText().toString(),etCode.getText().toString(),this);
                ChatClient.getIntance(this).sendMessage(msg);
                break;
        }
    }

    public void onEventMainThread(MsgObject msg) {
        if (msg.getC()== CmdEnum.REGISTER_CODE.getCmd()){
            //获取验证码
            Utils.showToast(this,"验证码："+msg.getM());
        }else if (msg.getC()== CmdEnum.REGISTER.getCmd()){
            //注册
            LoginBean loginBean = new Gson().fromJson(msg.getM(),LoginBean.class);
            if (loginBean.flag.equals("true")) {
                //注册成功
                finish();
            }
            Utils.showToast(this,loginBean.msg);
        }
    }


}
