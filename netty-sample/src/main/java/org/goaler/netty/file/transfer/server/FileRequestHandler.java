package org.goaler.netty.file.transfer.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

public class FileRequestHandler extends SimpleChannelInboundHandler<FileRegion> {


    protected void channelRead0(ChannelHandlerContext ctx, FileRegion msg) throws Exception {
        System.out.println("接收到：" + msg);
    }
}
