package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login,register;
    private EditText username,password;
    SystemDB DB;
    Spinner spinner;

    public static String currentUser,address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);
        DB = new SystemDB(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String id = username.getText().toString();
                String pw = password.getText().toString();

                String accounttype = spinner.getSelectedItem().toString();


                if (id.equals("")||pw.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter username and password",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (accounttype.equals("Buyer")){
                        Boolean validuser = DB.validateBuyer(id,pw);
                        if (validuser){
                            Toast.makeText(MainActivity.this, "Signed in Succesful!",Toast.LENGTH_SHORT).show();
                            currentUser = id;
                            address = DB.getAddress(id);
                            openManage();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Invalid Credential!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    else if (accounttype.equals("Delivery Guy")){
                        Boolean validuser = DB.validateDeliveryGuy(id,pw);
                        if (validuser){
                            Toast.makeText(MainActivity.this, "Signed in Succesful!",Toast.LENGTH_SHORT).show();
                            currentUser = id;
                            //address = DB.getAddress(id);
                            openDelivery();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Invalid Credential!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(accounttype.equals("Kitchen")){
                        if (id.equals("kitchen") && pw.equals("pw1")){
                            Toast.makeText(MainActivity.this, "Signed in Succesful!",Toast.LENGTH_SHORT).show();
                            openKitchen();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Invalid Credential!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(accounttype.equals("Admin")){
                        if (id.equals("admin") || pw.equals("pw1")){
                            Toast.makeText(MainActivity.this, "Signed in Succesful!",Toast.LENGTH_SHORT).show();
                            openAdmin();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Invalid Credential!",Toast.LENGTH_SHORT).show();
                        }
                    }



                }

            }
        });
    }

    public void openAdmin(){
        Intent intent5 = new Intent(this, Admin_main.class);
        startActivity(intent5);
    }

    public void openDelivery(){
        Intent intent4 = new Intent(this, DeliveryGuy_Home.class);
        startActivity(intent4);
    }

    public void openKitchen(){
        Intent intent3 = new Intent(this,Kitchen_ViewOrder.class);
        startActivity(intent3);
    }

    public void openRegister() {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void openManage() {
        Intent intent2 = new Intent (this,ManageFoodmain.class);
        startActivity(intent2);
    }

}