package com.example.foodorderingapp;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private String buyerID;
    private String address;
    private String foodID;
    private String orderStatus;
    private String deliveryGuyID;
    private String ETA;

    private List<Food> foodOrdered;

    private Cart myCart;
    private double price;
    SystemDB DB;

    /*public Order(int orderID){
        this.orderID = orderID;
        this.buyerID = "buyerID";
        this.address = "address";
        this.foodID = "foodID";
        this.orderStatus = "orderStatus";
        this.deliveryGuyID = "deliveryGuyID";
        this.ETA = "ETA";
        this.foodOrdered = generateList();
        this.myCart = new Cart(foodOrdered);
        this.price = myCart.calculatePrice();
    }*/

    public Order(int orderID, String buyerID, String address, String foodID, String orderStatus, String deliveryGuyID, String ETA,Double price) {
        this.orderID = orderID;
        this.buyerID = buyerID;
        this.address = address;
        this.foodID = foodID;
        this.orderStatus = orderStatus;
        this.deliveryGuyID = deliveryGuyID;
        this.ETA = ETA;
        this.price = price;
    }


    public double getPrice(){
        return price;
    }

    public Cart getMyCart() {
        return myCart;
    }

    public boolean isCompleted(){
        if (orderStatus.equals("Completed.")){
            return true;
        }
        else return false;
    }

    public List<Food> getFoodOrdered(){
        return foodOrdered;
    }

    @NonNull
    private List<Food> generateList(){
        String[] tempFood = foodID.split(",");
        List<Food> food = new ArrayList<>();
        List<Food> menu = DB.getFood();

        for(int i = 0; i < tempFood.length;i++){
            for (int j = 0; j<menu.size();j++){
                if (tempFood[i].equals(menu.get(j).getName())){
                    food.add(menu.get(j));
                    break;
                }
            }
        }
        return food;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public String getAddress() {
        return address;
    }

    public String getFoodID() {
        return foodID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getDeliveryGuyID() {
        return deliveryGuyID;
    }

    public String getETA() {
        return ETA;
    }



}
