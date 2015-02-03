package com.xqbase.gatekeeper.builder;

import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
 * The blocking netty server builder.
 *
 * @author Tony He
 */
public class BlockingNettyServerBuilder extends NettyServerBuilder<BlockingNettyServerBuilder, BlockingNettyServer> {

    public BlockingNettyServerBuilder(int port) {
        super(port);
    }

    @Override
    protected void configureBootstrap() {
        serverBootstrap.group(new OioEventLoopGroup())
                .channel(OioServerSocketChannel.class);
    }

    @Override
    protected BlockingNettyServer createServer() {
        return new BlockingNettyServer(serverHost, serverBootstrap);
    }
}
