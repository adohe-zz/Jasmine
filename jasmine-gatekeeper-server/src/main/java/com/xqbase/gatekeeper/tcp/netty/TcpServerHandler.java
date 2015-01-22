package com.xqbase.gatekeeper.tcp.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * TcpServerHandler handles inbound data and state changes
 * of all kinds.
 *
 * @author Tony He
 */
@ChannelHandler.Sharable
public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
