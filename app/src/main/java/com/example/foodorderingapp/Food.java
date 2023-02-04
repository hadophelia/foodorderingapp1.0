package com.example.foodorderingapp;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;
    private double price;
    private String ID;
    private String description;
    private int quantity;


    public Food(String name, Double price, String ID){
        this.name = name;
        this.price = price;
        this.ID = ID;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public String getID(){
        return this.ID;
    }

}

class Cart{
    private List<Food> cart = new ArrayList<>();

    public Cart(List<Food> cart){
        this.cart = cart;
    }

    public void addToCart(Food food){
        this.cart.add(food);
    }

    public double calculatePrice(){
        double total = 0;
        for(int i = 0; i<cart.size(); i++){
            total += cart.get(i).getPrice();
        }
        return total;
    }

    public boolean isEmpty(){
        if (cart.size() == 0){
            return true;
        }
        else return false;
    }

    public String toName(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cart.size(); i++){
            sb.append(cart.get(i).getName());
            if(i < cart.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cart.size(); i++){
                sb.append(cart.get(i).getID());
                if(i < cart.size() - 1) {
                    sb.append(",");
                }
            }
        return sb.toString();
    }
}