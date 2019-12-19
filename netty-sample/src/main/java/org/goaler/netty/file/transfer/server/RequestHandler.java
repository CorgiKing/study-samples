package org.goaler.netty.file.transfer.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.*;

public class RequestHandler extends ChannelInboundHandlerAdapter {

    FileOutputStream fo;

    {
        try {
            fo = new FileOutputStream("d:/tmp/file.test");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接建立成功，" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接断开，" + ctx.channel().remoteAddress());
    }

    int i = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收：" + msg);
        if (msg instanceof byte[]){
            System.out.println("接收次数："+ ++i);
            fo.write((byte[]) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读取完成，" + ctx.channel().remoteAddress());
    }
}
