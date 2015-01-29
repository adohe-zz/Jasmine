package com.xqbase.gatekeeper.tcp.gate;

/**
 * A base interface for GateFilters.
 *
 * @author Tony He
 */
public interface GateFilter {

    /**
     * To classify a filter by type. Standard types in GateKeeper are "pre"
     * for pre-routing filtering. "route" for routing to origin. "post" for
     * post-routing filtering. "error" for error handling. We also support
     * "static" filter for static response filtering.
     */
    String filterType();

    /**
     * filterOrder() must also be defined for a filter. Filters may have the
     * same filter order if precedence is not important for a filter. filterOrder
     * do not need to be sequential.
     */
    int filterOrder();

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
