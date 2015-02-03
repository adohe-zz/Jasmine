package com.xqbase.gatekeeper.builder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract Http Server based on Netty.
 *
 * @author Tony He
 */
public abstract class NettyServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private final ServerHost serverHost;
    private final ServerBootstrap serverBootstrap;
    private ChannelFuture serverShutdownFuture;

    protected NettyServer(ServerHost serverHost, ServerBootstrap serverBootstrap) {
        this.serverHost = serverHost;
        this.serverBootstrap = serverBootstrap;
    }

    public void start() throws Exception {
        startWithoutWaitingForShutdown();
        serverShutdownFuture.sync();
    }

    public void startWithoutWaitingForShutdown() throws Exception {
        Channel channel = serverBootstrap.bind().sync().channel();
        System.out.println("Netty based Http Server started at port: " + channel.localAddress());
        serverShutdownFuture = channel.closeFuture();
    }

    public void stop() throws InterruptedException {
        System.out.println("Shutdown server...");
        Future<?> acceptorTermFuture = serverBootstrap.group().shutdownGracefully();
        Future<?> workerTermFuture = serverBootstrap.childGroup().shutdownGracefully();

        System.out.println("Waiting for acceptor threads to stop.");
        acceptorTermFuture.sync();
        System.out.println("Waiting for worker threads to stop.");
        workerTermFuture.sync();
        System.out.println("Shutdown complete...");
    }

    protected abstract void addHandlerToPipeline(SocketChannel ch);
}
