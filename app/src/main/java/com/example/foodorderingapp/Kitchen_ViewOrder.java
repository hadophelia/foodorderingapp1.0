package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Kitchen_ViewOrder extends AppCompatActivity {

    SystemDB DB;
    Button refresh;

    private List<Order> myOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_view_order);
        DB = new SystemDB(this);

        myOrder = DB.getKitchenOrder();
        Button refresh = findViewById(R.id.refreshButton);

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
    }
}