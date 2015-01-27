package com.xqbase.gatekeeper.tcp.gate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is one of the core classes in Jasmine. It compiles, loads from
 * a file, and checks if source code changed.
 *
 * @author Tony He
 */
public class FilterLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterLoader.class);

    private static final FilterLoader INSTANCE = new FilterLoader();

    private final ConcurrentHashMap<String, Long> filterClassLastModified = new ConcurrentHashMap<String, Long>();

    private static final FilterRegistry FILTER_REGISTRY = FilterRegistry.getInstance();

    /**
     * @return Singleton Instance
     */
    public static FilterLoader getInstance() {
        return INSTANCE;
    }

    /**
     * From a file this will read the GateFilter source code, compile it, and add
     * it to the list of current filters. A true response means that it was successful.
     */
    public boolean putFilter(File file) {
        String name = file.getAbsolutePath() + file.getName();

        return true;
    }
}
