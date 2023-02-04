package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class Manage_Cart extends AppCompatActivity {

    private Button backToMain,checkOut;

    public Manage_Cart() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cart);

        backToMain = findViewById(R.id.backToMainButton);
        checkOut = findViewById(R.id.checkOutButton);

        List<Food> items = new LinkedList<>();
        items = ManageFoodmain.cart;
        //items.add("Pizza 1");
        //items.add("Pizza 2");
        //items.add("Pizza 3");
        //items.add("Pizza 4");

        RecyclerView recyclerView = findViewById(R.id.cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter adapter = new cartAdapter(items);
        recyclerView.setAdapter(adapter);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBackToMain();
            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ManageFoodmain.cart.size() == 0){
                    Toast.makeText(Manage_Cart.this, "Cart is Empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    setCheckOut();
                }
            }
        });

    }

    private void setBackToMain(){
        Intent intent = new Intent(this, ManageFoodmain.class);
        startActivity(intent);
    }

    private void setCheckOut(){
        Intent intent2 = new Intent(this, Checkout.class);
        startActivity(intent2);
    }
}