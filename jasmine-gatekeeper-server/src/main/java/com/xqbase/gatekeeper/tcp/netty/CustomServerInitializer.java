package com.xqbase.gatekeeper.tcp.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * This custom channel initializer adds our self channel handlers
 * to the channel pipeline.
 *
 * @author Tony He
 */
@ChannelHandler.Sharable
public class CustomServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

    }
}
