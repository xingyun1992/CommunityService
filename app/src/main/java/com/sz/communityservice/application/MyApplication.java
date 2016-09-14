package com.sz.communityservice.application;

import android.app.Application;

import com.sz.communityservice.immanage.ChatClient;
import com.sz.communityservice.utils.GlobalValue;
import com.sz.communityservice.utils.Utils;


public class MyApplication extends Application {

	/**
	 * 线上服务器
	 */
	public static final String host = "120.25.160.18";
	/**
	 * 端口号
	 */
	public static final int PORT = 11210;




//	//本机
//	public static final String host = "10.211.61.56";
//	/**
//	 * 端口号
//	 */
//	public static final int PORT = 11210;



	/**
	 * 全局变量存放地
	 */
	public static GlobalValue mGlobalValue;

	@Override
	public void onCreate() {
		super.onCreate();
		mGlobalValue = new GlobalValue();
		try {

			ChatClient.getIntance(this).start(host, PORT);
			Utils.printLog("连接到服务器...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
}
