package com.sz.communityservice.application;

import android.app.Application;

import com.sz.communityservice.immanage.ChatClient;
import com.sz.communityservice.utils.Utils;


public class MyApplication extends Application {

	private static final String host = "192.168.6.123";
	private static final int PORT = 11210;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		super.onCreate();
		try {
			ChatClient.getIntance(this).start(host, PORT);
			Utils.printLog("连接到服务器...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
}