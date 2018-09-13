package com.example.hotel_jpa.exceptions;

public class NoSuchRoomException extends Exception {
    public NoSuchRoomException() {
    }

    public NoSuchRoomException(String s) {
        super(s);
    }
}
