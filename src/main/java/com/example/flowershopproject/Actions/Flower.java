package com.example.flowershopproject.Actions;

import java.util.Objects;


public class Flower {

    private String floristName;
    private String flowerName;
    private String floristUsername;
    private String imageUrl;
    private int quantity;
    private double price;
    private int noOfItems;

    public Flower(){

    }
    public Flower(String floristName, String flowerName, String floristUsername, String imageUrl, int quantity, double price, int noOfItems) {
        this.floristName = floristName;
        this.flowerName = flowerName;
        this.floristUsername = floristUsername;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
        this.noOfItems=noOfItems;
    }

    public int getNoOfItems() {
        return noOfItems;
    }
    public String getStringNoOfItems() {
        return Integer.toString(noOfItems);
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public String getFloristName() {
        return floristName;
    }

    public void setFloristName(String floristName) {
        this.floristName = floristName;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getFloristUsername() {
        return floristUsername;
    }

    public void setFloristUsername(String floristUsername) {
        this.floristUsername = floristUsername;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getStringQuantity() {
        return Integer.toString(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getPrice() {
        return price;
    }

    public String getStringPrice() {
        return Double.toString(price);
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return quantity == flower.quantity &&
                Double.compare(flower.price, price) == 0 &&
                Objects.equals(floristName, flower.floristName) &&
                Objects.equals(flowerName, flower.flowerName) &&
                Objects.equals(floristUsername, flower.floristUsername) &&
                Objects.equals(imageUrl, flower.imageUrl);
    }

    public int hashCode() {
        return Objects.hash(floristName, flowerName, floristUsername, imageUrl, quantity, price);
    }

    @Override
    public String toString() {
        return flowerName + ", " + quantity + ", Flowers on stock: " + noOfItems + ", "  + this.price +" lei";
    }

}
