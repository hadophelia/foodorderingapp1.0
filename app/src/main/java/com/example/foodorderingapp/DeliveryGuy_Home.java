package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DeliveryGuy_Home extends AppCompatActivity {

    static String currentDG = MainActivity.currentUser;
    ArrayList<Order> taskList = new ArrayList<>();
    SystemDB DB;
    static Order currentOrder;
    Button refresh,myTask,logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_guy_home);

        DB = new SystemDB(this);
        taskList = DB.getDGOrder();
        currentOrder = DB.getOrder(currentDG);



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DeliveryAdapter adapter = new DeliveryAdapter(taskList);
        adapter.setContext(DeliveryGuy_Home.this);
        recyclerView.setAdapter(adapter);

        refresh = findViewById(R.id.refreshButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskList = DB.getDGOrder();
                DeliveryAdapter adapter = new DeliveryAdapter(taskList);
                adapter.setContext(DeliveryGuy_Home.this);
                recyclerView.setAdapter(adapter);
            }
        });

        myTask = findViewById(R.id.myTaskButton);
        myTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTask();
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
        currentOrder = null;
        Intent intent0 = new Intent(this,MainActivity.class);
        startActivity(intent0);

    }

    public void goToTask(){
        Intent intent4 = new Intent(this, DeliveryGuy_Task.class);
        startActivity(intent4);
    }
}