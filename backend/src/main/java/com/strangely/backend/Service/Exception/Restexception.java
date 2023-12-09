package com.strangely.backend.Service.Exception;
import org.springframework.http.HttpStatus;

public class Restexception extends RuntimeException {

    private final HttpStatus status;

    public Restexception(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}

