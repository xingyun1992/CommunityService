package com.sz.communityservice.base;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * 通用viewholder类，欢迎使用
 * @author 吴光明
 *
 */
public class ViewHolder {

	private SparseArray<View> mViews;
	private Context mContext;
	private View mConvertView;
	private int mposition;

	/**
	 * 构造方法，不允许在外使用
	 * @param context
	 * @param parent
	 * @param layoutid
	 * @param position
	 */
	private ViewHolder(Context context,ViewGroup parent,int layoutid,int position){
		mposition=position;
		mContext=context;
		mViews=new SparseArray<View>();
		mConvertView=LayoutInflater.from(context).inflate(layoutid, parent, false);
		mConvertView.setTag(this);
	}
	/**
	 * 获取viewholder的入口方法，需要在这判断
	 * @param context
	 * @param parent
	 * @param layoutid
	 * @param position
	 * @param convertView
	 * @return
	 */
	public static ViewHolder getHolder(Context context,ViewGroup parent,int layoutid,int position,View convertView){
		if (convertView==null) {
			return new ViewHolder(context, parent, layoutid, position);
		}else {
			ViewHolder holder=(ViewHolder) convertView.getTag();
			holder.mposition=position;
			return holder;
		}
	}
	/**
	 * 返回最后布局
	 * @return
	 */
	public View getconvertView(){
		return mConvertView;
	}
	
	/**
	 * 获取布局中的view
	 * @param viewid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewid){
		View view=mViews.get(viewid);
		if (view==null) {
			view=mConvertView.findViewById(viewid);
			mViews.put(viewid, view);
		}
		return (T) view;
		
	}
	/**
	 * 设置TextView的文本~
	 * @param id
	 * @param text
	 */
	public void setText(int id,String text){
		TextView textView=getView(id);
		textView.setText(text);
	}
	/**
	 * 网络加载图片在imageview上
	 * @param id
	 * @param url
	 */
	public void setImageUrl(int id,String url){
		ImageView imageView=getView(id);
		Glide.with(mContext).load(url).into(imageView);
	}
	/**
	 * 获取当前的位置
	 * @return
	 */
	public int getPosition() {
		return mposition;
	}
	
}
