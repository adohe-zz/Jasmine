package com.xqbase.gatekeeper.tcp;

/**
 * The abstract tcp gatekeeper server implements simple
 * lifecycle control.
 *
 * @author Tony He
 */
public abstract class AbstractServer {

    public AbstractServer() {

    }

    protected abstract void init() throws Exception;
    protected abstract void doStart() throws Exception;
    protected abstract void doClose() throws Exception;

    public void start() {

    }
}
