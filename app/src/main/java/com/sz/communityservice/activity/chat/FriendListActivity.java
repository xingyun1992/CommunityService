package com.sz.communityservice.activity.chat;

import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sz.communityservice.R;
import com.sz.communityservice.base.BaseActivity;

public class FriendListActivity extends BaseActivity {
    private ListView lvMain;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_friend_list);
        initTitle("好友列表");

        lvMain = (ListView) findViewById(R.id.lv_main);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String[] s = {"jack","paptr"};
        Adapter adapter = new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1,s);

    }
}
