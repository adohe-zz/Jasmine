package com.xqbase.gatekeeper.tcp.gate;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The filter registry center.
 *
 * @author Tony He
 */
public class FilterRegistry {

    private static final FilterRegistry INSTANCE = new FilterRegistry();

    private final ConcurrentHashMap<String, AbstractGateFilter> filters = new ConcurrentHashMap<String, AbstractGateFilter>();

    public static FilterRegistry getInstance() {
        return INSTANCE;
    }

    public AbstractGateFilter get(String key) {
        return this.filters.get(key);
    }

    public AbstractGateFilter remove(String key) {
        return this.filters.remove(key);
    }

    public void put(String key, AbstractGateFilter filter) {
        this.filters.putIfAbsent(key, filter);
    }

    public Collection<AbstractGateFilter> getAllFilters() {
        return this.filters.values();
    }
}
