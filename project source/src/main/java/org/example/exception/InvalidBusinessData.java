package org.example.exception;

public class InvalidBusinessData extends RuntimeException {

    public InvalidBusinessData(String message) {
        super(message);
    }

}
