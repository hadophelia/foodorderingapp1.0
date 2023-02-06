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
        menu = DB.getFood();
        //DB.clearOrders();

        RecyclerView recyclerView = findViewById(R.id.menu);
        showFood(recyclerView,menu);

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
                    if (menu.get(i).getName().toLowerCase().contains(name.toLowerCase())){
                        searchHit = true;
                        searchList.add(menu.get(i));
                        showFood(recyclerView,searchList);
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

    private void addFood() {
        DB.insertFood("F0001","Peperoni Pizza","If you have a love for spice, you must go for pizzas with pepperoni toppings.",18);
        DB.insertFood("F0002","Extravaganza Pizza","Pepperoni, ham, Italian sausage, beef, fresh onions, fresh green peppers, fresh mushrooms, and black olives — all sandwiched between two layers of cheese made with 100% real mozzarella.",18);
        DB.insertFood("F0003","Margherita Pizza"," A traditional Margherita pizza with fresh tomato sauce, mozzarella cheese and basil which represent the colours of the Italian flag – white cheese, green basil and red tomato.",18);
        DB.insertFood("F0004","Mac and Cheese","Deliciously rich and gooey, this baked mac and cheese is creamy, comforting, and PERFECT for the holidays, as well as a comforting family dinner!",16);
        DB.insertFood("F0005","Aglio e Olio","This recipe gets rich flavor from olive oil, garlic, and red pepper flakes.",14);
    }


}