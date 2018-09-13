package com.example.hotel_jpa.exceptions;

public class NoSuchClientException extends Throwable {
    public NoSuchClientException() {
    }

    public NoSuchClientException(String s) {
        super(s);
    }
}
