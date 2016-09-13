package com.sz.communityservice.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.sz.communityservice.R;
import com.sz.communityservice.adapter.ChatAdapter;
import com.sz.communityservice.application.MyApplication;
import com.sz.communityservice.base.BaseActivity;
import com.sz.communityservice.bean.ChatListItemBean;
import com.sz.communityservice.bean.ChatObject;
import com.sz.communityservice.bean.CmdEnum;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.immanage.ChatClient;
import com.sz.communityservice.immanage.MsgManage;
import com.sz.communityservice.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends BaseActivity {
    private EditText etMsg;
    private Button btSend;
    private ListView lvChat;
    private List<ChatListItemBean> chatListItemBeans;
    private ChatAdapter adapter;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_chat_list);

        etMsg = (EditText) findViewById(R.id.et_msg);
        btSend = (Button) findViewById(R.id.bt_send);
        lvChat = (ListView) findViewById(R.id.lv_chat);

    }

    @Override
    protected void initListener() {
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });
    }

    @Override
    protected void initData() {
        chatListItemBeans = new ArrayList<>();
        adapter = new ChatAdapter(chatListItemBeans,this);
        lvChat.setAdapter(adapter);
    }

    /**
     * 按下按钮发送消息
     */
    private void sendMsg(){
        String msg = etMsg.getText().toString();
        if (msg.length()== 0)
            return;

        ChatClient.getIntance(this).sendMessage(MsgManage.createChatMsg("peter", msg));
        ChatListItemBean chatListItemBean = new ChatListItemBean();
        chatListItemBean.msgtype = 0;
        chatListItemBean.textmsg = msg;
        chatListItemBean.userid = MyApplication.mGlobalValue.userid;
        chatListItemBeans.add(chatListItemBean);
        adapter.notifyDataSetChanged();

    }

    public void onEventMainThread(MsgObject msg) {
        if (msg.getC()== CmdEnum.CHAT.getCmd()){
            //接受到聊天消息,刷新界面
            try {
                ChatObject chatObject = new Gson().fromJson(msg.getM(), ChatObject.class);

                if (chatObject.getMsgFrom()== MyApplication.mGlobalValue.userid){
                    //自己发的不显示出来
                    Utils.printLog("发送成功！");
                    return;
                }

                ChatListItemBean chatListItemBean = new ChatListItemBean();
                chatListItemBean.msgtype = 1;
                chatListItemBean.textmsg = chatObject.getContent();
                chatListItemBean.userid = chatObject.getMsgFrom();
                chatListItemBeans.add(chatListItemBean);
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                //解析异常

            }

        }
    }



}
