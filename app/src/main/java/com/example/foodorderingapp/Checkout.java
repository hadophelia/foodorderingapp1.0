package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {

    public Cart finalCart = new Cart(ManageFoodmain.cart);
    public double price = finalCart.calculatePrice();

    SystemDB DB;

    TextView subtotal;
    RecyclerView recyclerView;
    EditText address;
    Button cancel,checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        DB = new SystemDB(this);
        address = findViewById(R.id.address);
        cancel = findViewById(R.id.cancelButton);
        checkout = findViewById(R.id.checkOutButton);

        address.setText(MainActivity.address);
        recyclerView = findViewById(R.id.recyclerView);
        subtotal = findViewById(R.id.Price);
        subtotal.setText("RM"+ Double.toString(price));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CheckoutAdapter adapter = new CheckoutAdapter(ManageFoodmain.cart);
        recyclerView.setAdapter(adapter);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!finalCart.isEmpty()){
                    setCheckout();
                }
            }
        });
    }

    public void cancel() {
        Intent intent = new Intent(this,ManageFoodmain.class);
        startActivity(intent);
    }

    public void setCheckout() {
        DB.createOrder(MainActivity.currentUser,address.getText().toString(),finalCart.toName(), finalCart.calculatePrice());
        Toast.makeText(Checkout.this, "Order Created.",Toast.LENGTH_SHORT).show();
        ManageFoodmain.cart.clear();
        Intent intent2 = new Intent(this,ManageFoodmain.class);
        startActivity(intent2);
    }
}