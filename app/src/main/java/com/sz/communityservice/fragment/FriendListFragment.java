package com.sz.communityservice.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.communityservice.R;
import com.sz.communityservice.base.BaseFragment;
import com.sz.communityservice.base.MySuperBaseAdapter;
import com.sz.communityservice.base.ViewHolder;
import com.sz.communityservice.bean.User;
import com.sz.communityservice.view.Sidebar;

import java.util.ArrayList;
import java.util.List;

public class FriendListFragment extends BaseFragment {

    private MyAdapter adapter;
    private List<User> contactList;
    private ListView listView;
    private boolean hidden;
    private Sidebar sidebar;

    private List<String> blackList;
    private TextView tv_unread;
    private TextView tv_total;
    private LayoutInflater infalter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_friendlist,container,false);
        listView = (ListView) view.findViewById(R.id.list);

        // 黑名单列表
        contactList = new ArrayList<User>();
        contactList.add(new User());
        contactList.add(new User());
        contactList.add(new User());
        // 获取设置contactlist
        infalter=LayoutInflater.from(getActivity());
        View headView = infalter.inflate(R.layout.item_contact_list_header,
                null);
        listView.addHeaderView(headView);
        View footerView = infalter.inflate(R.layout.item_contact_list_footer,
                null);
        listView.addFooterView(footerView);
//        sidebar = (Sidebar) getView().findViewById(R.id.sidebar);
//        sidebar.setListView(listView);
        tv_unread = (TextView) headView.findViewById(R.id.tv_unread);


        tv_total = (TextView) footerView.findViewById(R.id.tv_total);
        // 设置adapter
        adapter = new MyAdapter(contactList,mActivity,R.layout.item_contact_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position!=0&&position!=contactList.size()+1){

                }
            }
        });

        tv_total.setText(String.valueOf(contactList.size())+"位联系人");

        RelativeLayout re_newfriends=(RelativeLayout) headView.findViewById(R.id.re_newfriends);
        RelativeLayout re_chatroom=(RelativeLayout) headView.findViewById(R.id.re_chatroom);
        re_newfriends.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }

        });
        re_chatroom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }

        });
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        if (!hidden) {
            refresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hidden) {
            refresh();
        }
    }



    // 刷新ui
    public void refresh() {
        try {
            // 可能会在子线程中调到这方法
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                    tv_total.setText(String.valueOf(contactList.size())+"位联系人");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyAdapter extends MySuperBaseAdapter<User>{

        public MyAdapter(List<User> list, Context context, int layourid) {
            super(list, context, layourid);
        }

        @Override
        public void convert(ViewHolder viewHolder, User user, int position) {

        }
    }



}
