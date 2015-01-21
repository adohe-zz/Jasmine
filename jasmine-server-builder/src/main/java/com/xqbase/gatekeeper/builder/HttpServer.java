package com.xqbase.gatekeeper.builder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.Future;

/**
 * Abstract Http Server based on Netty.
 *
 * @author Tony He
 */
public abstract class HttpServer {

    private final ServerHost serverHost;
    private final ServerBootstrap serverBootstrap;
    private ChannelFuture serverShutdownFuture;

    protected HttpServer(ServerHost serverHost, ServerBootstrap serverBootstrap) {
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
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stop();
                } catch (InterruptedException e) {
                    System.out.println("Error while shutdown...");
                }
            }
        }));
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
