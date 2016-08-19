package com.sz.communityservice.immanage;

import com.google.gson.Gson;
import com.sz.communityservice.bean.MsgObject;
import com.sz.communityservice.utils.Utils;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

@Sharable
public class MsgObjectDecoder extends MessageToMessageDecoder<ByteBuf> {
	
	private Gson gson = new Gson();

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out)  {
		String msgObjStr = msg.toString(Charset.defaultCharset());
		try {
			MsgObject msgObject = gson.fromJson(msgObjStr, MsgObject.class);
			out.add(msgObject);
		}catch (Exception e){
			Utils.printLog("解析消息错误、消息内容为："+msgObjStr);
		}

	}

}
