package com.sayker.chatroom.handler.encoder;

import com.sayker.chatroom.dto.MessageDTO;
import com.sayker.chatroom.utility.ChatroomUtilies;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * the client transfer the server message to object
 * @author sayker
 */
public class MessageEncoder extends MessageToByteEncoder<MessageDTO> {


    @Override
    protected void encode(ChannelHandlerContext ctx, MessageDTO msg, ByteBuf out) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        String content = ChatroomUtilies.encodeMsg(msg);
        byteBuf.writeBytes(content.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }
}
