package com.xqbase.gatekeeper.tcp.gate;

/**
 * The Gate Filter Execution Status.
 *
 * @author Tony He
 */
public enum  ExecutionStatus {

    SUCCESS(1), SKIPPED(-1), DISABLED(-2), FAILED(-3);

    private int status;

    ExecutionStatus(int status) {
        this.status = status;
    }
}
