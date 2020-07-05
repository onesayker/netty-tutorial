package com.sayker.chatroom.handler.decoder;

import com.sayker.chatroom.dto.MessageDTO;
import com.sayker.chatroom.utility.ChatroomUtilies;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * the chat room handler
 * @author sayker
 */
public class ServerTransMsgHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String totalMsg = in.readCharSequence(in.readableBytes(), StandardCharsets.UTF_8).toString();
        String[] content = totalMsg.split("~");
        out.add(new MessageDTO(content[0], ChatroomUtilies.parseDateTime(content[1]),content[2]));
    }
}
