package com.example.flowershopproject.Exceptions;

public class FlowerNotInStock extends Exception{

    public FlowerNotInStock() {
        super(String.format("The flower is out of stock!"));
    }

}
