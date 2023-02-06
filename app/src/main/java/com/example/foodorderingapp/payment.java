package com.example.foodorderingapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class payment extends AppCompatActivity {

    Button checkOut, cancel;
    SystemDB DB;
    EditText nameF,nameL,address,country,city,zip,cardNo,CVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cancel = findViewById(R.id.cancelButton);
        checkOut = findViewById(R.id.payButton);
        DB = new SystemDB(this);
        nameF = findViewById(R.id.nameF);
        nameL = findViewById(R.id.nameL);
        address = findViewById(R.id.address);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        cardNo = findViewById(R.id.cardNo);
        CVV = findViewById(R.id.CVV);
        zip = findViewById(R.id.zip);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardNum = cardNo.getText().toString();
                String CVVtemp = CVV.getText().toString();
                if(nameF.getText().toString().equals("")||nameL.getText().toString().equals("")||address.getText().toString().equals("")||country.getText().toString().equals("")
                ||city.getText().toString().equals("")||zip.getText().toString().equals("")||CVVtemp.equals("")||cardNum.equals("")){
                    Toast.makeText(payment.this, "Please fill in all field.",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!cardNum.matches("[0-9]+")||cardNum.length() != 16){
                        Toast.makeText(payment.this, "Card Number must be of 16 digits.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(!CVVtemp.matches("[0-9]+")||CVVtemp.length() != 3){
                            Toast.makeText(payment.this, "Verification Number must be of 3 digits.",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            setCheckout();
                        }
                    }
                }


            }
        });


    }

    public void cancel() {
        Intent intent = new Intent(this,ManageFoodmain.class);
        startActivity(intent);
    }

    public void setCheckout() {
        DB.createOrder(MainActivity.currentUser,Checkout.address_final,Checkout.finalCart.toName(), Checkout.finalCart.calculatePrice());
        Toast.makeText(payment.this, "Order Created.",Toast.LENGTH_SHORT).show();
        ManageFoodmain.cart.clear();
        Intent intent2 = new Intent(this,ManageFoodmain.class);
        startActivity(intent2);
    }
}