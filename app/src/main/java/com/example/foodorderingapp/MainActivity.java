package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button managebutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        managebutton = findViewById(R.id.loginbutton);
        button = findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
        managebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmanage();
            }
        });
    }
    public void openRegister() {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void openmanage() {
        Intent intent2 = new Intent (this,ManageFoodmain.class);
        startActivity(intent2);
    }

}