package com.sz.communityservice.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sz.communityservice.R;
import com.sz.communityservice.base.BaseFragment;
import com.sz.communityservice.base.MySuperBaseAdapter;
import com.sz.communityservice.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends BaseFragment {
    private RelativeLayout rlError;
    private ListView listView;
    private ChatListAdapter adapter;
    private List<String> datalist;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_chat,container,false);
        rlError = (RelativeLayout) view.findViewById(R.id.rl_error);
        listView = (ListView) view.findViewById(R.id.list);

        return view;
    }

    @Override
    protected void initData() {
        datalist = new ArrayList<>();
        for (int i=0;i<10;i++){
            datalist.add("");
        }
        adapter = new ChatListAdapter(datalist,mActivity,R.layout.item_conversation_single);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    private class ChatListAdapter extends MySuperBaseAdapter<String>{

        public ChatListAdapter(List<String> list, Context context, int layourid) {
            super(list, context, layourid);
        }

        @Override
        public void convert(ViewHolder viewHolder, String s, int position) {

        }
    }
}
