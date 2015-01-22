package com.xqbase.gatekeeper.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The abstract tcp gatekeeper server implements simple
 * lifecycle control.
 *
 * @author Tony He
 */
public abstract class AbstractServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServer.class);

    protected String appName;

    public AbstractServer() throws Exception {
        appName = "tcp-gatekeeper";

        LOGGER.info("Initializing the {} ...", appName);

        try {
            init();

            LOGGER.info("Has initialized the {} ...", appName);
        } catch (Exception e) {
            LOGGER.error("Failed to initialize the " + appName, e);
            throw e;
        }
    }

    protected abstract void init() throws Exception;
    protected abstract void doStart() throws Exception;
    protected abstract void doClose() throws Exception;

    public void start() throws Exception {
        LOGGER.info("Starting the {} ...", appName);

        try {
            doStart();
        } catch (Exception e) {
            LOGGER.error("Failed to start the " + appName, e);
            throw e;
        }
        LOGGER.info("Started the {} ...", appName);
    }

    public void close() {
        LOGGER.info("Stopping the {} ...", appName);

        try {
            doClose();
        } catch (Exception e) {
            LOGGER.error("Failed to stop the " + appName, e);
        }
    }
}
