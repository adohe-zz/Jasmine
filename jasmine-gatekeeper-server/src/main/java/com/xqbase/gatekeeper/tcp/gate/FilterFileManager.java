package com.xqbase.gatekeeper.tcp.gate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages the directory polling for changes
 * and new filters.
 *
 * @author Tony He
 */
public class FilterFileManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterFileManager.class);

    private String[] aDirectories;
    private int pollingInterval;

    private Thread poller;

    private static FilterFileManager INSTANCE;

    private FilterFileManager() {
    }

    public static FilterFileManager getInstance() {
        return INSTANCE;
    }

    /**
     * Initialize the FilterFileManager.
     */
    public static void init(int pollingInterval, String... directories) throws Exception {
        if (INSTANCE == null)
            INSTANCE = new FilterFileManager();

        INSTANCE.pollingInterval = pollingInterval;
        INSTANCE.aDirectories = directories;
    }


}
