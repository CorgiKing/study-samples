package org.goaler.netty.file.transfer.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileTransferClientApplication {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
//                        pipeline.addLast(new StringEncoder());
//                        pipeline.addLast(new StringDecoder());
//                        pipeline.addLast(new ObjectEncoder());
                    }
                });

        Channel channel = bootstrap.connect("localhost", 2019).sync().channel();
        System.out.println("连接成功...");


        try {
            FileInputStream fis = new FileInputStream("D:\\file.txt");

            FileRegion fileRegion = new DefaultFileRegion(fis.getChannel(), 0, fis.available());

            channel.writeAndFlush(fileRegion);

            System.out.println("发送结束！！！");

            channel.flush();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
