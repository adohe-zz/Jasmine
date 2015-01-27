package com.xqbase.gatekeeper.tcp.gate;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The filter registry center.
 *
 * @author Tony He
 */
public class FilterRegistry {

    private static final FilterRegistry INSTANCE = new FilterRegistry();

    private final ConcurrentHashMap<String, GateFilter> filters = new ConcurrentHashMap<String, GateFilter>();

    public static FilterRegistry getInstance() {
        return INSTANCE;
    }

    public GateFilter get(String key) {
        return this.filters.get(key);
    }

    public GateFilter remove(String key) {
        return this.filters.remove(key);
    }

    public void put(String key, GateFilter filter) {
        this.filters.putIfAbsent(key, filter);
    }
}
