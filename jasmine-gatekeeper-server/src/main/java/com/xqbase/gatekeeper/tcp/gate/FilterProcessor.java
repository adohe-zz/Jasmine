package com.xqbase.gatekeeper.tcp.gate;

import com.xqbase.gatekeeper.tcp.gate.context.GateException;
import com.xqbase.gatekeeper.tcp.gate.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This is the core class to execute filters.
 *
 * @author Tony He
 */
public class FilterProcessor {

    private static FilterProcessor INSTANCE = new FilterProcessor();
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterProcessor.class);

    private FilterProcessor() {
    }

    public static FilterProcessor getInstance() {
        return INSTANCE;
    }

    /**
     * Runs all "pre" filters. These filters are run before routing to the origin.
     * @throws GateException
     */
    public void preRoute() throws GateException {

    }

    /**
     * Runs all "route" filters. These filters route calls to the origin.
     * @throws GateException
     */
    public void route() throws GateException {

    }

    /**
     * Runs all "post" filters. These filters are run after routing to the origin.
     * GateException from GateFilter are thrown. Any other Throwable are caught and
     * a GateException is thrown out with a 500 status code.
     * @throws GateException
     */
    public void postRoute() throws GateException {

    }

    /**
     * Runs all "error" filters. These are called only if an exception occurs.
     * Exceptions from this are swallowed and logged so as not to bubble up.
     */
    public void error() {

    }

    /**
     * Runs all filters of the filterType sType.
     * @throws Throwable
     */
    public Object runFilters(String type) throws Throwable {
        boolean bResult = false;
        List<AbstractGateFilter> list = FilterLoader.getInstance().getFiltersByType(type);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                AbstractGateFilter gateFilter = list.get(i);

            }
        }

        return bResult;
    }

    /**
     * Processes an individual GateFilter. This method adds Debug information. Any uncaught Throwable
     * are caught by this method and converted to a GateException with a 500 status code.
     * @throws GateException
     */
    public Object processGateFilter(AbstractGateFilter gateFilter) throws GateException {
        RequestContext ctx = RequestContext.getCurrentContext();
        boolean debug = ctx.debugRouting();
        long execTime = 0;
        String filterName = "";

        try {
            long ltime = System.currentTimeMillis();
            filterName = gateFilter.getClass().getSimpleName();

            RequestContext copy = null;
            Object o = null;
            Throwable t = null;

            if (debug) {

            }

            GateFilterResult result = gateFilter.runFilter();
            ExecutionStatus status = result.getStatus();
            execTime = System.currentTimeMillis() - ltime;

            switch (status) {
                case FAILED:
                    t = result.getException();
                    ctx.addFilterExecutionSummary(filterName, ExecutionStatus.FAILED.name(), execTime);
                    break;
                case SUCCESS:
                    o = result.getResult();
                    ctx.addFilterExecutionSummary(filterName, ExecutionStatus.SUCCESS.name(), execTime);
                    break;
                default:
                    break;
            }

            if (t != null) throw t;

            return o;
        } catch (Throwable e) {
            if (e instanceof GateException) {
                throw (GateException) e;
            } else {
                GateException ex = new GateException(e, "Filter threw Exception", 500, gateFilter.filterType() + ":" + filterName);
                ctx.addFilterExecutionSummary(filterName, ExecutionStatus.FAILED.name(), execTime);
                throw ex;
            }
        }
    }
}
