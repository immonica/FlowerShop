package com.example.flowershopproject.Actions;

import com.example.flowershopproject.Services.UserService; //folder exact

import java.util.Objects;

public class Order {

    private String clientUsername;
    private Flower flower;
    private int orderNo;
    private String status;

    public Order(){

    }
    public Order(String clientUsername, Flower flower, int orderNo, String status) {
        this.clientUsername = clientUsername;
        this.flower = flower;
        this.orderNo = orderNo;
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public Flower getFlower() {return flower;};

    public void setFlower(Flower flower) {this.flower = flower;};

    public int getOrderNo() { return orderNo;}

    public void setOrderNo(int orderNo) { this.orderNo = orderNo;};

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNo == order.orderNo &&
                Objects.equals(clientUsername, order.clientUsername) &&
                Objects.equals(flower, order.flower);

    }

    public int hashCode() {
        return Objects.hash(clientUsername, flower, orderNo);
    }

    @Override
    public String toString() {
        try {
            return "Order no: " + this.orderNo + ", Flower: " + this.flower.getFlowerName() + ", Price: " + this.flower.getPrice() + ", Writer: " + this.flower.getFloristName() + ", Client: " + UserService.getUser(this.clientUsername).getName() + ", Status: " + this.status;
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
