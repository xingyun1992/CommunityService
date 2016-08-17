package com.sz.communityservice.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	
	protected Activity mActivity;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mActivity=activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=initView(inflater, container);
		initData();
		initListener();
		return view;
	}

	protected abstract View initView(LayoutInflater inflater, ViewGroup container) ;
	protected abstract void initData() ;
	protected abstract void initListener() ;
}
