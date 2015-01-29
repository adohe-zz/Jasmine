package com.xqbase.gatekeeper.tcp.gate;

/**
 * This class represents the gate filter executes
 * result.
 *
 * @author Tony He
 */
public class GateFilterResult {

    private Object result;
    private Throwable exception;
    private ExecutionStatus status;

    public GateFilterResult(Object result, ExecutionStatus status) {
        this.result = result;
        this.status = status;
    }

    public GateFilterResult(ExecutionStatus status) {
        this.status = status;
    }

    public GateFilterResult() {
        this.status = ExecutionStatus.DISABLED;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(ExecutionStatus status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
