package com.ndirituedwin.Exceptions;

public class ServerNotFoundException extends RuntimeException {
    public ServerNotFoundException(String s) {
        super(s);
    }
}
