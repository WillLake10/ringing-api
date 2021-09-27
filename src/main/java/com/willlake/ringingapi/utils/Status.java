package com.willlake.ringingapi.utils;

import org.springframework.http.HttpStatus;

public class Status {
    private HttpStatus httpStatus;
    private String message;

    public Status(HttpStatus requestStatus, String message) {
        this.httpStatus = requestStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
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
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
