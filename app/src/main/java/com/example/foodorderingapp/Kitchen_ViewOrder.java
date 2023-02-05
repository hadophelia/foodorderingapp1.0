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

public class Kitchen_ViewOrder extends AppCompatActivity {

    SystemDB DB;
    Button refresh,logOut;

    private List<Order> myOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_view_order);
        DB = new SystemDB(this);

        myOrder = DB.getKitchenOrder();
        refresh = findViewById(R.id.refreshButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        KitchenAdapter adapter = new KitchenAdapter(myOrder);
        adapter.setContext(Kitchen_ViewOrder.this);
        recyclerView.setAdapter(adapter);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOrder = DB.getKitchenOrder();
                KitchenAdapter adapter = new KitchenAdapter(myOrder);
                adapter.setContext(Kitchen_ViewOrder.this);
                recyclerView.setAdapter(adapter);
                //DB.requestDelivery(1);
            }
        });

        logOut = findViewById(R.id.logoutButton);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLogOut();
            }
        });


    }

    public void setLogOut(){
        MainActivity.currentUser = null;
        MainActivity.address = null;
        Intent intent0 = new Intent(this,MainActivity.class);
        startActivity(intent0);
    }

}