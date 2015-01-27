package com.xqbase.gatekeeper.tcp.gate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.List;
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
    private final ConcurrentHashMap<String, List<GateFilter>> hashFiltersByType = new ConcurrentHashMap<String, List<GateFilter>>();

    private static final FilterRegistry FILTER_REGISTRY = FilterRegistry.getInstance();
    private static DynamicCodeCompiler COMPILER;
    private static FilterFactory FILTER_FACTORY = new DefaultFilterFactory();

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
    public boolean putFilter(File file) throws Exception {
        String name = file.getAbsolutePath() + file.getName();
        if (filterClassLastModified.get(name) != null && (file.lastModified() != filterClassLastModified.get(name))) {
            LOGGER.debug("reloading filter " + name);
            FILTER_REGISTRY.remove(name);
        }

        GateFilter filter = FILTER_REGISTRY.get(name);
        if (filter == null) {
            Class clazz = COMPILER.compile(file);
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                filter = (GateFilter) FILTER_FACTORY.newInstance(clazz);
            }
        }
        return true;
    }

    private List<GateFilter> getFiltersByType(String filterType) {
        List<GateFilter> filters = hashFiltersByType.get(filterType);

        if (filters != null)
            return filters;

        return filters;
    }
}
