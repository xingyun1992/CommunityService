/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.sz.communityservice.immanage;

import android.content.Context;

import com.sz.communityservice.application.MyApplication;
import com.sz.communityservice.utils.Utils;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class SecureChatClientInitializer extends ChannelInitializer<SocketChannel> {


    private final SslContext sslCtx;
    private  Context context;

    public SecureChatClientInitializer(SslContext sslCtx,Context context) {
        this.sslCtx = sslCtx;
        this.context = context;
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Utils.printLog("与服务器断开连接服务器");
  
        //重新连接服务器  
        ctx.channel().eventLoop().schedule(new Runnable() {  
            @Override  
            public void run() {  
            	ChatClient.getIntance(context).reConnect();
            }  
        }, 20, TimeUnit.SECONDS);  
        ctx.close(); 
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        Utils.printLog("客户端channel初始化");
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(sslCtx.newHandler(ch.alloc(), MyApplication.host, MyApplication.PORT));

        pipeline.addLast(new MsgObjectDecoder());
        pipeline.addLast(new MsgObjectEncoder());
//        pipeline.addLast(new LoggingHandler(LogLevel.INFO));

        // and then business logic.
        pipeline.addLast(new SecureChatClientHandler());
    }


}
