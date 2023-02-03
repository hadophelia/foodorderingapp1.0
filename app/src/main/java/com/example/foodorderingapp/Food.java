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

    public int getQuantity(){
        return quantity;
    }

    public void addQuantity(){
        this.quantity ++;
    }

    public void reduceQuantity(){
        this.quantity --;
    }
}

class Cart{
    private List<Food> cart = new ArrayList<>();

    public Cart(){
    }

    public void addToCart(Food food){
        this.cart.add(food);
    }

    public void checkIfDelete(){ //when the quantity is updated,
        for(int i = 0; i < cart.size();i++){
            if (cart.get(i).getQuantity() == 0){
                cart.remove(i);
            }
        }
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