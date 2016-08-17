package com.sz.communityservice.immanage;


import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import de.greenrobot.event.EventBus;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
 
/**
 * 客户端 channel
 * 
 * @author waylau.com
 * @date 2015-2-26
 */
public class SimpleChatClientHandler extends  SimpleChannelInboundHandler<String> {
	
	private Handler handler;
	public SimpleChatClientHandler(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
		Message message = new Message();
		
		EventBus.getDefault().post(s);
		
		Gson gson = new Gson();
		

	}
}
