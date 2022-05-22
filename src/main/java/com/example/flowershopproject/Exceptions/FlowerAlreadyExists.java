package com.example.flowershopproject.Exceptions;

public class FlowerAlreadyExists extends Exception{
    private String flowerName;

    public FlowerAlreadyExists(String flowerName) {
        super(String.format("An identical product called '%s' already exists!", flowerName));
        this.flowerName = flowerName;
    }

    public String getFlowerName() {
        return this.flowerName;
    }
}
