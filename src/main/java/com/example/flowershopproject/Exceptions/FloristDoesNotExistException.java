package com.example.flowershopproject.Exceptions;

public class FloristDoesNotExistException extends Exception {
    private String name;

    public void WriterDoesNotExist(String name) {
       // super(String.format("A florist with the username '%s' does not exist", name));
        this.name = name;
    }

    public String getUsername() {
        return this.name;
    }

}
