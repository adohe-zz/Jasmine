package com.xqbase.gatekeeper.tcp.gate.context;

/**
 * All handled exceptions in GateKeeper are GateExceptions.
 *
 * @author Tony He
 */
public class GateException extends Exception {

    public int statusCode;
    public String errorCause;

    public GateException(Throwable throwable, String message, int statusCode, String errorCause) {
        super(message, throwable);
        this.statusCode = statusCode;
        this.errorCause = errorCause;
        incrementCounter("GateKeeper::EXCEPTION:" + errorCause + ":" + statusCode);
    }

    public GateException(String message, int statusCode, String errorCause) {
        super(message);
        this.statusCode = statusCode;
        this.errorCause = errorCause;
        incrementCounter("GateKeeper::EXCEPTION:" + errorCause + ":" + statusCode);
    }

    public GateException(Throwable throwable, int statusCode, String errorCause) {
        super(throwable.getMessage(), throwable);
        this.statusCode = statusCode;
        this.errorCause = errorCause;
        incrementCounter("GateKeeper::EXCEPTION:" + errorCause + ":" + statusCode);
    }

    private static final void incrementCounter(String name) {

    }
}
