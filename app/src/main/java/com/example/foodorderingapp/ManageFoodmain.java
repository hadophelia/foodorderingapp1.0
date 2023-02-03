package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ManageFoodmain extends AppCompatActivity {

    private Button gotoCart;

    public static List<Food> cart = new ArrayList<>();

    SystemDB DB = new SystemDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_foodmain);
        gotoCart = findViewById(R.id.toCartButton);

        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotocart();
            }
        });

        //todo: items  arraylist with all the food inside from database

        Food Pizza1 = new Food("Peperoni",12.5,"F0001");
        Food Pizza2 = new Food("Hawaiian Chicken",12.5,"F0002");
        Food Pizza3 = new Food("Ranch",12.5,"F0003");

        List<Food> food = new ArrayList<>();
        food.add(Pizza1);
        food.add(Pizza2);
        food.add(Pizza3);



        RecyclerView recyclerView = findViewById(R.id.menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodAdapter adapter = new FoodAdapter(food);
        recyclerView.setAdapter(adapter);

        //todo: function if search button is clicked, recycle view adapter = new food adapter(search item), set new adapter



    }

    public void gotocart() {
        Intent intent = new Intent(this,Manage_Cart.class);
        startActivity(intent);
    }


    private void addFood() {
        DB.insertFood("F0001","Peperoni Pizza","A pizza",25);
        DB.insertFood("F0002","Extravanganza","Another pizza",25);
        DB.insertFood("F0003","Ranch","Another pizza",25);
    }


}