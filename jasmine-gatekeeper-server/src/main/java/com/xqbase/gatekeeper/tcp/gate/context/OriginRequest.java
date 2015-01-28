package com.xqbase.gatekeeper.tcp.gate.context;

/**
 * Origin Request Interface.
 *
 * @author Tony He
 */
public interface OriginRequest<T> {

    T getOrigin();
}
