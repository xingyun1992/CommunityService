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

import com.google.gson.Gson;
import com.sz.communityservice.bean.CmdEnum;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.utils.Utils;

import de.greenrobot.event.EventBus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 */
public class SecureChatClientHandler extends SimpleChannelInboundHandler<MsgObject> {

	private final ByteBuf firstMessage;

	public SecureChatClientHandler() {
		/*firstMessage = Unpooled.buffer(EchoClient.SIZE);
		for (int i = 0; i < firstMessage.capacity(); i++) {
			firstMessage.writeByte((byte) i);
		}*/
		MsgObject msgObj = new MsgObject();
		Gson gson = new Gson();
		msgObj.setC(CmdEnum.HEART_BEAT.getCmd());
		msgObj.setM("o");
		String msg = gson.toJson(msgObj);
		firstMessage = Unpooled.copiedBuffer(msg.getBytes());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		Utils.printLog("链接建立成功，第一次发消息：" + firstMessage.toString());
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MsgObject msg) throws Exception {
		Utils.printLog("客户端读到消息：" + msg.getM()+"  消息类型："+msg.getC());

		EventBus.getDefault().post(msg);

		//心跳检测
		if (CmdEnum.HEART_BEAT.getCmd() == msg.getC()) {
			MsgObject msgObj = new MsgObject();
			Gson gson = new Gson();
			msgObj.setC(CmdEnum.HEART_BEAT.getCmd());
			msgObj.setM("o");
			String msg2 = gson.toJson(msgObj);
			ByteBuf buf = Unpooled.copiedBuffer(msg2.getBytes());
			ctx.writeAndFlush(buf);
		}
	}
}
