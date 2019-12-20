package org.goaler.netty.file.transfer.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class FileTransferServerApplication {

    public static void main(String[] args) throws Exception{

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //parentGroup处理accept事件，childGroup处理读写事件
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup =new NioEventLoopGroup();

        serverBootstrap.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new RequestHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture f = serverBootstrap.bind(2019).sync();
            System.out.println("服务开启...");

            f.channel().closeFuture().sync();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }
}
