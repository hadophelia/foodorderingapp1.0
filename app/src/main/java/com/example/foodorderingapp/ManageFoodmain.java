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

    public static List<String> cart = new ArrayList<>();

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

        //todo: items  arraylist with all the food inside
        //todo: make the cartlist, arraylist generic type == food

        List<String> items = new LinkedList<>();
        items.add("Pizza 1");
        items.add("Pizza 2");
        items.add("Pizza 3");
        items.add("Pizza 4");

        RecyclerView recyclerView = findViewById(R.id.menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodAdapter adapter = new FoodAdapter(items);
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