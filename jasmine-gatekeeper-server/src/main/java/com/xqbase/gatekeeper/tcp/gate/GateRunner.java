package com.xqbase.gatekeeper.tcp.gate;

import com.xqbase.gatekeeper.tcp.gate.context.OriginRequest;
import com.xqbase.gatekeeper.tcp.gate.context.OriginResponse;
import com.xqbase.gatekeeper.tcp.gate.context.RequestContext;

/**
 * This class initializes the servlet requests and responses into the
 * RequestContext and wraps the FilterProcessor calls to preRoute(),
 * route(), postRoute() and error() methods.
 *
 * @author Tony He
 */
public class GateRunner {

    /**
     * Constructor
     */
    public GateRunner() {

    }

    public void init(OriginRequest request, OriginResponse response) {
        RequestContext.getCurrentContext().setOriginRequest(request);
        RequestContext.getCurrentContext().setOriginResponse(response);
    }
}