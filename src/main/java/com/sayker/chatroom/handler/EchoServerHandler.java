package com.sayker.chatroom.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * a simple nio echo server
 * @author sayker
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    @java.lang.Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println(byteBuf.toString(Charset.forName("utf-8")));
        ctx.writeAndFlush(msg);
    }

    @java.lang.Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

