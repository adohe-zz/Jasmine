package com.xqbase.gatekeeper.tcp.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This internal NettyServer handles TCP connections with
 * asynchronous mode.
 *
 * @author Tony He
 */
public class NettyServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private final NioEventLoopGroup bossGroup;
    private final NioEventLoopGroup workerGroup;
    private CustomServerInitializer customServerInitializer;

    private List<Channel> channelList = new CopyOnWriteArrayList<Channel>();

    private final int port = 8080;

    public NettyServer() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        customServerInitializer = new CustomServerInitializer();

        ServerBootstrap b = new ServerBootstrap();
        b.childOption(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(customServerInitializer);
        channelList.add(b.bind(port).sync().channel());
    }

    public void close() throws InterruptedException {
        try {
            for (Channel channel : channelList) {
                channel.close();
            }
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
