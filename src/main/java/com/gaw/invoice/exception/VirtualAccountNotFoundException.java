package com.gaw.invoice.exception;

public class VirtualAccountNotFoundException extends Exception {
    public VirtualAccountNotFoundException() {
    }

    public VirtualAccountNotFoundException(String message) {
        super(message);
    }
}
