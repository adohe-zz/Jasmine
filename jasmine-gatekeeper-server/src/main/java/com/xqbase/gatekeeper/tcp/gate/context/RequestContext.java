package com.xqbase.gatekeeper.tcp.gate.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The Request Context holds request, response, state information and data for GateFilters
 * to access and share. The RequestContext lives for the duration of the request and is
 * thread local.
 *
 * @author Tony He
 */
public class RequestContext extends ConcurrentHashMap<String, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestContext.class);

    protected static Class<? extends RequestContext> contextClass = RequestContext.class;

    protected static final ThreadLocal<? extends RequestContext> threadLocal = new ThreadLocal<RequestContext>() {
        @Override
        protected RequestContext initialValue() {
            try {
                return contextClass.newInstance();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    };

    public RequestContext() {
        super();
    }

    public static void setContextClass(Class<? extends RequestContext> clazz) {
        contextClass = clazz;
    }

    public static RequestContext getCurrentContext() {
        return threadLocal.get();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean b = (Boolean) get(key);
        if (b != null) {
            return b.booleanValue();
        }

        return defaultValue;
    }

    public void set(String key) {
        set(key, Boolean.TRUE);
    }

    public void set(String key, Object value) {
        if (value != null)
            put(key, value);
        else
            remove(key);
    }

    /**
     * Unset the thread local context. Done at the end of the request.
     */
    public void unset() {
        threadLocal.remove();
    }
}
