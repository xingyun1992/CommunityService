package com.sz.communityservice.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
/**
 * 超级adapter，只适用于一种布局的listview和gridview
 * @author 吴光明
 *
 * @param <T>
 */
public abstract class MySuperBaseAdapter<T> extends BaseAdapter{
	
	private List<T> list;
	private Context mContext;
	private int layoutid;
	
	public MySuperBaseAdapter(List<T> list,Context context,int layourid){
		this.list=list;
		this.mContext=context;
		this.layoutid=layourid;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=ViewHolder.getHolder(mContext, parent, layoutid, position, convertView);
		convert(viewHolder,list.get(position),position);
		return viewHolder.getconvertView();
	}
	/**
	 * 为布局中各个控件填充数据
	 * @param viewHolder
	 * @param t
	 */
	public abstract void convert(ViewHolder viewHolder,T t,int position);

}
