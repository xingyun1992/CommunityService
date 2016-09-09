package com.sz.communityservice.immanage;

import android.content.Context;

import com.sz.communityservice.application.MyApplication;
import com.sz.communityservice.utils.Utils;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * 做成单例，在application中启动连接、
 * @author kaifa
 *
 */
public class ChatClient {
	public Channel channel ;

	private Thread mThread;

	private Context context;
	private Bootstrap bootstrap;

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
	}

	public void sendMessage(String msg) {
		Utils.printLog("发出的消息："+msg);
		ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
		ChannelFuture lastWriteFuture=channel.writeAndFlush(buf);
	}

	public void start(final String host, final int port) throws Exception {
//		final SslContext sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
//				.build();

		mThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				EventLoopGroup group = new NioEventLoopGroup();
				try {
					
					 bootstrap = new Bootstrap().group(group)
							.channel(NioSocketChannel.class)
							.handler(new SecureChatClientInitializer(null,context));
					
					channel = bootstrap.connect(host, port).sync().channel();
					
					while (true) {
//						channel.flush();
						//线程不死！！！
						Thread.sleep(3000);
					}
					
				
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					group.shutdownGracefully();
				}

			}
		});

		mThread.setName("连接线程");
		mThread.start();

	}

	/**
	 * 判断是否连着，若没有连接，则去重连
	 */
	public void isConnect(){
		if (channel.isOpen()){
			Utils.printLog("维持连接中...");
		}else {
			Utils.printLog("连接断开，准备重连...");
			reConnect();
		}
	}

	/**
	 * 断线重连,
	 */
	public  void reConnect() {
		Utils.printLog("断线重连");
		ChannelFuture future = null;
		try {
			future = bootstrap.connect(MyApplication.host, MyApplication.PORT).sync();
			future.addListener(reConnectListener);
		} catch (Exception e) {
			e.printStackTrace();
			// future.addListener(channelFutureListener);
			future.removeListener(reConnectListener);
			Utils.printLog("重连异常");
		}
	}

	// add reconnect listener
	ChannelFutureListener reConnectListener = new ChannelFutureListener() {
		@Override
		public void operationComplete(ChannelFuture future) throws Exception {
			if (future.isSuccess()) {
				channel.close();
				channel = future.channel();
				Utils.printLog("重新连接服务器成功");
			} else {
				Utils.printLog("重新连接服务器失败");
				// 3秒后重新连接
				future.channel().eventLoop().schedule(new Runnable() {
					@Override
					public void run() {
						reConnect();
					}
				}, 3, TimeUnit.SECONDS);
			}
		}
	};

	/**
	 * 断开连接,似乎是同步？
	 */
	public void disConnect() throws InterruptedException {
		channel.closeFuture().sync();
	}

}
