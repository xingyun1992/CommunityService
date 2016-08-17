package com.sz.communityservice.immanage;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 全局handle
 * 
 * @author kaifa
 * 
 */
public class MyHandle extends Handler {

	private Context context;

	public MyHandle(Context context) {
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {
		// 处理消息的msg
		Intent intent =null;
		switch (msg.what) {
		case 1:
			//登录到房间
			
			intent = new Intent("com.xingyun.login");
			intent.putExtra("obj", (String)(msg.obj));
			context.sendBroadcast(intent);
			Log.v("xingyun", "发送广播1成功");
			break;
		case 2:
			//进入房间
			intent = new Intent("com.xingyun.inroom");
			intent.putExtra("obj", (String)(msg.obj));
			context.sendBroadcast(intent);
			Log.v("xingyun", "发送广播2成功");
			
			break;
		case 3:
			//准备
			
			
			break;
		case 4:
			//下子
			intent = new Intent("com.xingyun.RivalPoint");
			context.sendBroadcast(intent);
			Log.v("xingyun", "发送广播2成功");
			
			break;
		default:
			
			
			break;
		}

	}

}
