package com.sz.communityservice.immanage;

import android.content.Context;

import com.google.gson.Gson;
import com.sz.communityservice.application.MyApplication;
import com.sz.communityservice.bean.ChatObject;
import com.sz.communityservice.bean.CmdEnum;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.bean.RegisterCode;
import com.sz.communityservice.bean.User;
import com.sz.communityservice.utils.Utils;

/**
 * Created by xingyun on 2016/8/19.
 * 创建消息管理
 */
public class MsgManage {

    /**
     * 创建登录消息
     * @param username
     * @param password
     * @return
     */
    public static String createLoginMsg(String username,String password){
        MsgObject msgObj = new MsgObject();
        Gson gson = new Gson();
        User user = new User();
        user.setAccount(username);
        user.setPwd(password);
        msgObj.setC(CmdEnum.LOGIN.getCmd());
        msgObj.setM(gson.toJson(user));
        String msg = gson.toJson(msgObj);
        return msg;
    }

    /**
     * 创建聊天消息,（文本）
     * @return
     */
    public static String createChatMsg(String frieadId,String text){
        MsgObject msgObj = new MsgObject();
        ChatObject co = new ChatObject();
        Gson gson = new Gson();
        co.setContent(text);
        co.setMsgType("text");
        co.setMsgTo(frieadId);
        co.setMsgFrom(MyApplication.mGlobalValue.userid);
        co.setCreateTime(System.currentTimeMillis());
        msgObj.setC(CmdEnum.CHAT.getCmd());
        msgObj.setM(gson.toJson(co));
        String msg = gson.toJson(msgObj);
        return msg;
    }

    /**
     * 获取验证码
     * @param mobile
     * @return
     */
    public static String createGetCodeMsg(String mobile){
        MsgObject msgObj = new MsgObject();
        RegisterCode rc = new RegisterCode();
        Gson gson = new Gson();
        rc.setMobile(mobile);
        rc.setSign("xx");
        msgObj.setC(CmdEnum.REGISTER_CODE.getCmd());
        msgObj.setM(gson.toJson(rc));
        return gson.toJson(msgObj);
    }

    /**
     *  注册信息
     * @param mobile
     * @param code
     * @param context
     * @return
     */
    public static String createRegisterMsg(String mobile,String code,Context context){
        MsgObject msgObj = new MsgObject();
        RegisterCode rc = new RegisterCode();
        Gson gson = new Gson();
        rc.setMobile(mobile);
        rc.setImei(Utils.getUid(context));
        rc.setSign("xx");
        rc.setCode(code);
        msgObj.setC(CmdEnum.REGISTER.getCmd());
        msgObj.setM(gson.toJson(rc));
        return gson.toJson(msgObj);
    }





}
