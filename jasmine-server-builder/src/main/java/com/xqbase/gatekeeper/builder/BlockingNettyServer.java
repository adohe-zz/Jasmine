package com.xqbase.gatekeeper.builder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.SocketChannel;

/**
 * BlockingNettyServer adapts blocking I/O model.
 *
 * @author Tony He
 */
public class BlockingNettyServer extends NettyServer {

    protected BlockingNettyServer(ServerHost serverHost, ServerBootstrap serverBootstrap) {
        super(serverHost, serverBootstrap);
    }

    @Override
    protected void addHandlerToPipeline(SocketChannel ch) {

    }
}
