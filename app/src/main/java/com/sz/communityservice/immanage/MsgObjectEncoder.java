package com.sz.communityservice.immanage;

import com.google.gson.Gson;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.utils.Utils;

import java.util.List;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

@Sharable
public class MsgObjectEncoder extends MessageToMessageEncoder<MsgObject> {
	
	private Gson gson = new Gson();

	@Override
	protected void encode(ChannelHandlerContext ctx, MsgObject msg, List<Object> out) throws Exception {
		Utils.printLog("进来了encode但是msg=null");
		if (null == msg) {
			return;
		}
		String jsonmsg = gson.toJson(msg);
		Utils.printLog("encoder："+jsonmsg);
		out.add(jsonmsg);
	}

}
