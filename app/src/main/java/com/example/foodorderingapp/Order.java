package com.example.foodorderingapp;

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
    private List<Food> menu = ManageFoodmain.menu;

    private Cart myCart;

    public Order(int orderID){
        this.orderID = orderID;
        this.buyerID = "buyerID";
        this.address = "address";
        this.foodID = "foodID";
        this.orderStatus = "orderStatus";
        this.deliveryGuyID = "deliveryGuyID";
        this.ETA = "ETA";
        //this.foodOrdered = generateList();
        //this.myCart = new Cart(foodOrdered);
    }

    public Order(int orderID, String buyerID, String address, String foodID, String orderStatus, String deliveryGuyID, String ETA) {
        this.orderID = orderID;
        this.buyerID = buyerID;
        this.address = address;
        this.foodID = foodID;
        this.orderStatus = orderStatus;
        this.deliveryGuyID = deliveryGuyID;
        this.ETA = ETA;
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

    private List<Food> generateList(){
        String[] tempFood = foodID.split(",");
        List<Food> food = new ArrayList<>();
        for (int i = 0; i < menu.size();i++) {
            for (int j = 0; j < tempFood.length;j++){
                if (tempFood[j].equals(menu.get(i))){
                    food.add(menu.get(i));
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
