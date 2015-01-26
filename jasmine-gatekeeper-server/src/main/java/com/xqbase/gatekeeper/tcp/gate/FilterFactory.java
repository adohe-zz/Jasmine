package com.xqbase.gatekeeper.tcp.gate;

/**
 * FilterFactory creates filter instance through
 * factory pattern.
 *
 * @author Tony He
 */
public interface FilterFactory {

    AbstractGateFilter newInstance(Class clazz) throws Exception;
}
