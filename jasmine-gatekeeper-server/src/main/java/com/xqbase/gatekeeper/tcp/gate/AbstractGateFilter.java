package com.xqbase.gatekeeper.tcp.gate;

/**
 * Base abstract class for GateFilters. This base class
 * defines other abstract class.
 *
 * @author Tony He
 */
public abstract class AbstractGateFilter implements GateFilter, Comparable<AbstractGateFilter> {

    /**
     * By default GateFilters are static, they don't carry state.
     */
    public boolean isStaticFilter() {
        return true;
    }

    @Override
    public int compareTo(AbstractGateFilter filter) {
        return this.filterOrder() - filter.filterOrder();
    }
}
