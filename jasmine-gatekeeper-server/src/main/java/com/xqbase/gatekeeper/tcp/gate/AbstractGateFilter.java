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

    /**
     * runFilter checks !isFilterDisabled() and shouldFilter(). The run() method is
     * invoked if both are true.
     */
    public GateFilterResult runFilter() {
        GateFilterResult result;
        if (shouldFilter()) {
            try {
                Object res = run();
                result = new GateFilterResult(res, ExecutionStatus.SUCCESS);
            } catch (Throwable t) {
                result = new GateFilterResult(ExecutionStatus.FAILED);
                result.setException(t);
            }
        } else {
            result = new GateFilterResult(ExecutionStatus.SKIPPED);
        }

        return result;
    }

    @Override
    public int compareTo(AbstractGateFilter filter) {
        return this.filterOrder() - filter.filterOrder();
    }
}
