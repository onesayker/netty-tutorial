package com.sayker.chatroom.handler;

import com.sayker.chatroom.dto.MessageDTO;
import com.sayker.chatroom.utility.ChatroomUtilies;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;
import java.util.Scanner;

/**
 * server msg handler
 *
 * @author sayker
 */
public class ServerMsgHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("sayker-client come in the chat room!");
        MessageDTO messageDTO = new MessageDTO("SERVER", new Date(), "Hello I'm sayker-server side!");
        ByteBuf buf = ctx.alloc().buffer();
        String content = ChatroomUtilies.encodeMsg(messageDTO);
        buf.writeBytes(content.getBytes());
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            MessageDTO messageDTO = (MessageDTO) msg;
            ChatroomUtilies.printMsg(messageDTO);
            Scanner scanner = new Scanner(System.in);
            System.out.println("sayker server, please input the msg");
            String reply = scanner.nextLine();
            MessageDTO message = new MessageDTO("Sever", new Date(), reply);
            ctx.writeAndFlush(message);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
