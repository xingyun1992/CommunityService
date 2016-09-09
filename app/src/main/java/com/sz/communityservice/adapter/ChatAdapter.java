package com.sz.communityservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sz.communityservice.R;
import com.sz.communityservice.bean.ChatListItemBean;

import java.util.List;

/**
 * Created by xingyun on 2016/9/7.
 */
public class ChatAdapter extends BaseAdapter{

    List<ChatListItemBean> list;
    Context context;

    public ChatAdapter(List<ChatListItemBean> list,Context context){
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (list==null)
            return 0;
        else
            return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).msgtype;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null) {
            if (getItemViewType(position) == 0){
                convertView = LayoutInflater.from(context).inflate(R.layout.mychatlist_item,parent,false);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.tv_seedmsg_text);
                convertView.setTag(holder);
            }else if (getItemViewType(position)==1){
                convertView = LayoutInflater.from(context).inflate(R.layout.youchatlist_item,parent,false);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.tv_receive_text);
                convertView.setTag(holder);
            }
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(list.get(position).textmsg);

        return convertView;
    }

    class ViewHolder {
        ImageView headview;
        TextView textView;
    }

}
