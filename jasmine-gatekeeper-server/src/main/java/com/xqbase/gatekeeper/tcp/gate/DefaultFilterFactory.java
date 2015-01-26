package com.xqbase.gatekeeper.tcp.gate;

/**
 * The default implementation of {@link com.xqbase.gatekeeper.tcp.gate.FilterFactory}
 *
 * @author Tony He
 */
public class DefaultFilterFactory implements FilterFactory {

    @Override
    public AbstractGateFilter newInstance(Class clazz) throws Exception {
        return (AbstractGateFilter) clazz.newInstance();
    }
}
