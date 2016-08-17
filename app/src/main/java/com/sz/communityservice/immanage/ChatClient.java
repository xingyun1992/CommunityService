package com.sz.communityservice.immanage;

import android.content.Context;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * 做成单例，在application中启动连接、
 * @author kaifa
 *
 */
public class ChatClient {
	private Channel channel ;

	private Thread mThread;

	private Context context;
	
	private MyHandle mhandler;
	
	private static ChatClient mChatClient;
	
	public static synchronized ChatClient getIntance(Context context) {
		if (mChatClient==null) {
			mChatClient=new ChatClient(context);
		}
		return mChatClient;
		
	}
	public ChatClient(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		mhandler=new MyHandle(context);
	}

	public void sendMessage(String msg) {
		channel.writeAndFlush(msg+"\r\n");
	}

	public void start(final String host, final int port) throws Exception {


		mThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				EventLoopGroup group = new NioEventLoopGroup();
				try {
					
					Bootstrap bootstrap = new Bootstrap().group(group)
							.channel(NioSocketChannel.class)
							.handler(new SimpleChatClientInitializer(mhandler));
					
					channel = bootstrap.connect(host, port).sync().channel();
					
					while (true) {
//						channel.flush();
						Thread.sleep(3000);
					}
					
				
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					group.shutdownGracefully();
				}

			}
		});

		mThread.start();

	}
}
