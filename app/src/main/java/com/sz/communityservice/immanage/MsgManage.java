package com.sz.communityservice.immanage;

import com.google.gson.Gson;
import com.sz.communityservice.bean.ChatObject;
import com.sz.communityservice.bean.CmdEnum;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.bean.User;

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
        co.setMsgFrom("jack");
        co.setCreateTime(System.currentTimeMillis());
        msgObj.setC(CmdEnum.CHAT.getCmd());
        msgObj.setM(gson.toJson(co));
        String msg = gson.toJson(msgObj);
        return msg;
    }



}
