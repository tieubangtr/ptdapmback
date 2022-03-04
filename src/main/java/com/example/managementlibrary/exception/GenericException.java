package com.example.managementlibrary.exception;


public class GenericException extends RuntimeException {

    private String message;

    public GenericException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
