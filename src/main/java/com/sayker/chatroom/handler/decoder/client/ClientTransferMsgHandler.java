package com.sayker.chatroom.handler.decoder.client;

import com.sayker.chatroom.dto.MessageDTO;
import com.sayker.chatroom.utility.ChatroomUtilies;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * client byte to messageDTO
 * @author sayker
 */
public class ClientTransferMsgHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] buff = new byte[2024];
        int length = in.readableBytes();
        in.readBytes(buff, 0, length);

        String totalMsg = new String(buff, 0, length, StandardCharsets.UTF_8);
        String[] content = totalMsg.split("~");
        out.add(new MessageDTO(content[0], ChatroomUtilies.parseDateTime(content[1]), content[2]));


    }
}
