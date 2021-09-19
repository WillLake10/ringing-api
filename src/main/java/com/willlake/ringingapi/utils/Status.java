package com.willlake.ringingapi.utils;

public class Status {
    private RequestStatus requestStatus;
    private String message;

    public Status(RequestStatus requestStatus, String message) {
        this.requestStatus = requestStatus;
        this.message = message;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Status{" +
                "requestStatus=" + requestStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
