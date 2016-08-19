package com.sz.communityservice.immanage;

import com.google.gson.Gson;
import com.sz.communityservice.bean.MsgObject;

import java.util.List;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

@Sharable
public class MsgObjectEncoder extends MessageToMessageEncoder<MsgObject> {
	
	private Gson gson = new Gson();

	@Override
	protected void encode(ChannelHandlerContext ctx, MsgObject msg, List<Object> out) throws Exception {
		if (null == msg) {
			return;
		}
		out.add(gson.toJson(msg));
	}

}
