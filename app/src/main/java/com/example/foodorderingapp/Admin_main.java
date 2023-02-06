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

public class Admin_main extends AppCompatActivity {

    ArrayList<Order> myOrder = new ArrayList<>();
    SystemDB DB;
    Button logOut, Search,refresh;
    EditText order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        DB = new SystemDB(this);
        myOrder = DB.getAllOrder();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdminAdapter adapter = new AdminAdapter(myOrder);
        adapter.setContext(Admin_main.this);
        recyclerView.setAdapter(adapter);

        logOut = findViewById(R.id.logoutButton);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLogOut();
            }
        });

        Search = findViewById(R.id.searchButton);
        order = findViewById(R.id.orderID);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderID = order.getText().toString();
                Order order = DB.findOrder(orderID);
                    if(order!=null){
                        ArrayList<Order> tempOrder = new ArrayList<>();
                        tempOrder.add(order);
                        AdminAdapter adapter = new AdminAdapter(tempOrder);
                        adapter.setContext(Admin_main.this);
                        recyclerView.setAdapter(adapter);
                    }
                    else{
                        Toast.makeText(Admin_main.this, "Order not found.",Toast.LENGTH_SHORT).show();

                    }
            }
        });

        refresh = findViewById(R.id.refreshButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOrder = DB.getAllOrder();
                AdminAdapter adapter = new AdminAdapter(myOrder);
                adapter.setContext(Admin_main.this);
                recyclerView.setAdapter(adapter);
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