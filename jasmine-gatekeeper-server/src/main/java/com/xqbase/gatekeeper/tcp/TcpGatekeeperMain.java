package com.xqbase.gatekeeper.tcp;

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

    private static final Logger logger = LoggerFactory.getLogger(TcpGatekeeperMain.class);

    public static void main(String[] args) {

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

        logger.info("STARTUP_MSG:\n" +
                        "*******************************************\n" +
                        "\tStarting : {}\n" +
                        "\tHost : {}\n" +
                        "\tArgs : {}\n" +
                        "*******************************************",
                className,hostName, Arrays.toString(args));
    }
}
