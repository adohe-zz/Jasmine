package com.xqbase.gatekeeper.tcp.gate;

/**
 * A base interface for GateFilters.
 *
 * @author Tony He
 */
public interface GateFilter {

    /**
     * a "true" return from this method means that the run() method should
     * be invoked.
     */
    boolean shouldFilter();

    /**
     * If the shouldFilter() is true, this method will be invoked. This
     * method is the core method of the GateFilter.
     */
    Object run();
}
