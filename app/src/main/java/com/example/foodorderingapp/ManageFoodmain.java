package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ManageFoodmain extends AppCompatActivity {

    private Button gotoCart,search,logOut,toOrders;
    EditText foodName;

    public static List<Food> cart = new ArrayList<>();
    public static List<Food> menu = new ArrayList<>();
    private List<Food> searchList = new ArrayList<>();

    SystemDB DB = new SystemDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_foodmain);
        gotoCart = findViewById(R.id.toCartButton);
        search = findViewById(R.id.searchButton);
        foodName = findViewById(R.id.foodName);
        logOut = findViewById(R.id.logoutButton);
        toOrders = findViewById(R.id.toOrdersButton);



        //todo: items  arraylist with all the food inside from database

        menu = createMenu();


        RecyclerView recyclerView = findViewById(R.id.menu);
        showFood(recyclerView,menu);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //FoodAdapter adapter = new FoodAdapter(menu);
        //adapter.setContext(ManageFoodmain.this);
        //recyclerView.setAdapter(adapter);

        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotocart();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLogOut();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchList.clear();
                boolean searchHit = false;
                String name = foodName.getText().toString();
                //TODO: fix the search method so that it works for shortform
                for (int i = 0; i < menu.size();i++){
                    if (menu.get(i).getName().toLowerCase().matches(name.toLowerCase())){
                        searchHit = true;
                        searchList.add(menu.get(i));
                        showFood(recyclerView,searchList);
                        //FoodAdapter searchAdapter = new FoodAdapter(searchList);
                        //searchAdapter.setContext(ManageFoodmain.this);
                        //recyclerView.setAdapter(searchAdapter);
                    }
                }
                if (!searchHit){
                    showFood(recyclerView,menu);
                    Toast.makeText(ManageFoodmain.this, "Sorry, food not found.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //todo: function if search button is clicked, recycle view adapter = new food adapter(search item), set new adapter

        toOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToOrders();
            }
        });

    }

    private void setToOrders(){
        Intent intent0 = new Intent(this,Buyer_Orders.class);
        startActivity(intent0);
    }

    public void setLogOut(){
        MainActivity.currentUser = null;
        MainActivity.address = null;
        Intent intent0 = new Intent(this,MainActivity.class);
        startActivity(intent0);
    }

    public void gotocart() {
        Intent intent = new Intent(this,Manage_Cart.class);
        startActivity(intent);
    }

    private void showFood(RecyclerView recyclerView,List<Food> food){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FoodAdapter adapter = new FoodAdapter(food);
        adapter.setContext(ManageFoodmain.this);
        recyclerView.setAdapter(adapter);
    }

    private List<Food> createMenu(){
        Food Pizza1 = new Food("Peperoni",12.5,"F0001");
        Food Pizza2 = new Food("Hawaiian Chicken",12.5,"F0002");
        Food Pizza3 = new Food("Ranch",12.5,"F0003");

        List<Food> food = new ArrayList<>();
        food.add(Pizza1);
        food.add(Pizza2);
        food.add(Pizza3);

        return food;
    }

    private void addFood() {
        DB.insertFood("F0001","Peperoni Pizza","A pizza",25);
        DB.insertFood("F0002","Extravanganza","Another pizza",25);
        DB.insertFood("F0003","Ranch","Another pizza",25);
    }


}