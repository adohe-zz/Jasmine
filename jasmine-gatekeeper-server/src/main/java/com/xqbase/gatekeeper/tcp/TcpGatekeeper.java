package com.xqbase.gatekeeper.tcp;

import com.xqbase.gatekeeper.tcp.netty.NettyServer;

/**
 * This class builds the real tcp gatekeeper server, handles
 * tcp connections, acts as a gatekeeper.
 *
 * @author Tony He
 */
public class TcpGatekeeper extends AbstractServer {

    private NettyServer nettyServer;

    public TcpGatekeeper() throws Exception {}

    @Override
    protected void init() throws Exception {
        nettyServer = new NettyServer();
    }

    @Override
    protected void doStart() throws Exception {
    }

    @Override
    protected void doClose() throws Exception {
        nettyServer.close();
    }
}
