package com.xqbase.gatekeeper.tcp;

import com.xqbase.tools.util.ShutdownHookManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * The only entry point for the gatekeeper server.
 *
 * @author Tony He
 */
public class TcpGatekeeperMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(TcpGatekeeperMain.class);
    private static final String APPLICATION_ID = "tcp-gatekeeper";

    public static void main(String[] args) {

        printStartupAndShutdownMsg(args);
        TcpGatekeeper gatekeeper = null;
        try {
            gatekeeper = new TcpGatekeeper();
            gatekeeper.start();

            final TcpGatekeeper finalTcpGatekeeper = gatekeeper;
            ShutdownHookManager.getInstance().addShutdownHook(new Runnable() {
                @Override
                public void run() {
                    finalTcpGatekeeper.close();
                }
            }, Integer.MAX_VALUE);
        } catch (Exception e) {
            if (gatekeeper != null) {
                gatekeeper.close();
            }
            LOGGER.error("can not start tcp gatekeeper then is shutdown", e);
        }
    }

    private static void printStartupAndShutdownMsg(String[] args) {
        String host = "unknow";
        try {
            host = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        final String hostName = host;
        final String className = TcpGatekeeperMain.class.getSimpleName();

        LOGGER.info("STARTUP_MSG:\n" +
                        "*******************************************\n" +
                        "\tStarting : {}\n" +
                        "\tHost : {}\n" +
                        "\tArgs : {}\n" +
                        "*******************************************",
                className,hostName, Arrays.toString(args));

        ShutdownHookManager.getInstance().addShutdownHook(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("SHUTDOWN_MSG:\n" +
                                "*******************************************\n" +
                                "\tShutting down : {}\n" +
                                "\tHost : {}\n" +
                                "*******************************************",
                        className,hostName);
            }
        }, 1);
    }
}
