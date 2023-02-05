package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeliveryGuy_Task extends AppCompatActivity {

    TextView orderInfo;
    Order myOrder = DeliveryGuy_Home.currentOrder;
    SystemDB DB;
    Button setETA,back,completeOrder;
    EditText ETA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_guy_task);
        orderInfo = findViewById(R.id.orderInfo);
        DB = new SystemDB(this);
        myOrder = DB.getOrder(DeliveryGuy_Home.currentDG);
        setETA = findViewById(R.id.setETA);
        back = findViewById(R.id.backButton);
        completeOrder = findViewById(R.id.orderCompleted);
        ETA = findViewById(R.id.ETA);


        if (myOrder != null){
            StringBuilder order = new StringBuilder();
            order.append("Order Number:" + myOrder.getOrderID());
            order.append("\nFood(s): " + myOrder.getFoodID());
            order.append("\nAddress: " + myOrder.getAddress());
            order.append("\nETA: " + myOrder.getETA());
            order.append("\nBuyer Phone Number: " + DB.getBuyerPhone(myOrder.getBuyerID()));

            orderInfo.setText(order.toString());


            setETA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String time = ETA.getText().toString();
                    DB.setETA(myOrder.getOrderID(),time);
                    myOrder = DB.getOrder(DeliveryGuy_Home.currentDG);
                    StringBuilder order = new StringBuilder();
                    order.append("Order Number:" + myOrder.getOrderID());
                    order.append("\nFood(s): " + myOrder.getFoodID());
                    order.append("\nAddress: " + myOrder.getAddress());
                    order.append("\nETA: " + myOrder.getETA());
                    orderInfo.setText(order.toString());
                }
            });



            completeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DB.completeOrder(myOrder.getOrderID());
                    Toast.makeText(DeliveryGuy_Task.this, "Delivery Completed!",Toast.LENGTH_SHORT).show();

                }
            });
        }

        else{
            orderInfo.setText("No Ongoing Task, go back to Dashboard.");
            orderInfo.setTextSize(48);
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });



    }

    private void back(){
        Intent intent = new Intent(this,DeliveryGuy_Home.class);
        startActivity(intent);
    }
}