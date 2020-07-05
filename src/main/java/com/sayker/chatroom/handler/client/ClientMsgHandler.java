package com.sayker.chatroom.handler.client;

import com.sayker.chatroom.dto.MessageDTO;
import com.sayker.chatroom.utility.ChatroomUtilies;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class ClientMsgHandler extends SimpleChannelInboundHandler<MessageDTO> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageDTO msg) throws Exception {

        try {
            ChatroomUtilies.printMsg(msg);
            Scanner scanner = new Scanner(System.in);
            System.out.println("sayker client, please input the msg:");
            String reply = scanner.nextLine();
            MessageDTO message = new MessageDTO("CLIENT", new Date(), reply);
            ByteBuf buffer = ctx.alloc().buffer();
            String content = message.getUsername() + "~" + ChatroomUtilies.formatDateTime(message.getSentTime()) + "~" + message.getMsg();
            buffer.writeBytes(content.getBytes(StandardCharsets.UTF_8));
            ctx.writeAndFlush(buffer);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
