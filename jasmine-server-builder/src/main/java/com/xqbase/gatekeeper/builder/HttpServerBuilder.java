package com.xqbase.gatekeeper.builder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;

/**
 * Abstract builder factory for {@link com.xqbase.gatekeeper.builder.HttpServer}
 *
 * @author Tony He
 */
public abstract class HttpServerBuilder<T extends HttpServerBuilder, S extends HttpServer> {

    protected final ServerBootstrap serverBootstrap;
    protected ServerHost serverHost;

    public HttpServerBuilder(int port) {
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.localAddress(port);
    }

    public T serverHost(ServerHost serverHost) {
        this.serverHost = serverHost;
        return returnBuilder();
    }

    public <O> T serverSocketOption(ChannelOption<O> channelOption, O value) {
        serverBootstrap.option(channelOption, value);
        return returnBuilder();
    }

    public <O> T clientSocketOption(ChannelOption<O> channelOption, O value) {
        serverBootstrap.childOption(channelOption, value);
        return returnBuilder();
    }

    public S build() {
        configureBootstrap();
        S server = createServer();
        return server;
    }

    private T returnBuilder() {
        return (T) this;
    }

    protected abstract void configureBootstrap();

    protected abstract S createServer();
}
