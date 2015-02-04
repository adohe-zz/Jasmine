package com.xqbase.gatekeeper.builder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;

/**
 * Abstract builder factory for {@link NettyServer}
 *
 * @author Tony He
 */
public abstract class NettyServerBuilder<T extends NettyServerBuilder, S extends NettyServer> {

    protected final ServerBootstrap serverBootstrap;
    protected ServerHost serverHost;

    public NettyServerBuilder(int port) {
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.localAddress(port);
    }

    public T serverHost(ServerHost serverHost) {
        this.serverHost = serverHost;
        return (T) this;
    }

    public <O> T serverSocketOption(ChannelOption<O> channelOption, O value) {
        serverBootstrap.option(channelOption, value);
        return (T) this;
    }

    public <O> T clientSocketOption(ChannelOption<O> channelOption, O value) {
        serverBootstrap.childOption(channelOption, value);
        return (T) this;
    }

    public T handler(ChannelHandler handler) {
        serverBootstrap.handler(handler);
        return (T) this;
    }

    public T childHandler(ChannelInitializer<Channel> channelInitializer) {
        serverBootstrap.childHandler(channelInitializer);
        return (T) this;
    }

    public S build() {
        configureBootstrap();
        S server = createServer();
        return server;
    }

    protected abstract void configureBootstrap();

    protected abstract S createServer();
}
