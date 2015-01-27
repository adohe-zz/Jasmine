package com.xqbase.gatekeeper.tcp.gate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private boolean running = true;

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
        INSTANCE.manageFiles();
        INSTANCE.startPoller();
    }

    public static void shutdown() {
        INSTANCE.stopPoller();
    }

    /**
     * Returns a list of all files from polled directory.
     */
    private List<File> getFiles() {
        List<File> files = new ArrayList<File>();
        for (String dict : aDirectories) {
            if (dict != null) {
                File directory = getDirectory(dict);
                File[] aFiles = directory.listFiles();
                if (aFiles != null) {
                    files.addAll(Arrays.asList(aFiles));
                }
            }
        }

        return files;
    }

    /**
     * Returns the directory file for the given path. A Runtime Exception
     * will throw if the directory is not invalid.
     */
    private File getDirectory(String path) {
        File dict = new File(path);
        if (!dict.isDirectory()) {
            URL resource = FilterFileManager.class.getClassLoader().getResource(path);
            try {
                dict = new File(resource.toURI());
            } catch (Exception e) {
                LOGGER.error("Error accessing directory in class loader. path=" + path, e);
            }
            if (!dict.isDirectory()) {
                throw new RuntimeException(dict.getAbsolutePath() + " is not a valid directory");
            }
        }
        return dict;
    }

    /**
     * Start the polling for filters.
     */
    private void startPoller() {
        poller = new Thread("FilterFileManagerPoller") {
            @Override
            public void run() {
                while (running) {
                    try {
                        sleep(pollingInterval * 1000);
                        manageFiles();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        poller.start();
    }

    private void stopPoller() {
        running = false;
    }

    private void manageFiles() {
        List<File> files = getFiles();
        processFilterFile(files);
    }

    private void processFilterFile(List<File> files) {
    }
}
