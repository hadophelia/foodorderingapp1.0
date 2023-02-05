package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Buyer_Orders extends AppCompatActivity {

    SystemDB DB;
    Button backToMain;

    private List<Order> myOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_orders);
        DB = new SystemDB(this);
        myOrder = DB.getBuyerOrder(MainActivity.currentUser);
        //myOrder.add(new Order(1));
        //myOrder = DB.getOrderBuyer(MainActivity.currentUser);
        //Order myOr = DB.getOrderBuyer(MainActivity.currentUser);
        Button backToMain = findViewById(R.id.backToMainButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderAdapter adapter = new OrderAdapter(myOrder);
        adapter.setContext(Buyer_Orders.this);
        recyclerView.setAdapter(adapter);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMain();
            }
        });
    }

    private void goToMain(){
        Intent intent = new Intent(this,ManageFoodmain.class);
        startActivity(intent);
    }
}