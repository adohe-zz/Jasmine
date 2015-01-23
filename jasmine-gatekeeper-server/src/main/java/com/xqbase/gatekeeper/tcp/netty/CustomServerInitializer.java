package com.xqbase.gatekeeper.tcp.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import java.util.concurrent.TimeUnit;

/**
 * This custom channel initializer adds our self channel handlers
 * to the channel pipeline.
 *
 * @author Tony He
 */
@ChannelHandler.Sharable
public class CustomServerInitializer extends ChannelInitializer<SocketChannel> {

    private int readTimeout = 1500;
    private int writeTimeout = 1500;

    private final TcpServerHandler serverHandler;

    public CustomServerInitializer() {
        serverHandler = new TcpServerHandler();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS));
        p.addLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS));
        p.addLast(serverHandler);
    }
}
