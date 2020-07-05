package com.sayker.chatroom.app;

import com.sayker.chatroom.handler.ServerMsgHandler;
import com.sayker.chatroom.handler.TimeServerHandler;
import com.sayker.chatroom.handler.decoder.ServerTransMsgHandler;
import com.sayker.chatroom.handler.encoder.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatroomServerApp {

    private int port;

    public int getPort() {
        return port;
    }

    public ChatroomServerApp(int port) {
        this.port = port;
    }


    public void run() throws Exception {
        NioEventLoopGroup bossLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossLoopGroup, workLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new MessageEncoder(),
                                    new ServerTransMsgHandler(),
                                    new ServerMsgHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024*10)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatroomServerApp(8083).run();
    }
}
