package com.example.hotel_jpa.exceptions;

public class NoSuchCheckinException extends Exception {
    public NoSuchCheckinException() {
    }

    public NoSuchCheckinException(String s) {
        super(s);
    }
}
