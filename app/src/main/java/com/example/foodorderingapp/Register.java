package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private Button back, register;
    private EditText username,password,password2,email,phoneNumber,address;
    SystemDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        back = findViewById(R.id.backButton);
        register = findViewById(R.id.registerButton);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        password2 = (EditText)findViewById(R.id.confirmPassword);
        email = (EditText)findViewById(R.id.email);
        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        address = (EditText)findViewById(R.id.address);
        DB = new SystemDB(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = username.getText().toString();
                String pw = password.getText().toString();
                String pw2 = password2.getText().toString();
                String email1 = email.getText().toString();
                String phoneNo = phoneNumber.getText().toString();
                String address1 = address.getText().toString();


                if (id.equals("")||pw.equals("")||pw2.equals("")||email1.equals("")||phoneNo.equals("")||address1.equals("")){
                    Toast.makeText(Register.this, "Please enter all field",Toast.LENGTH_SHORT).show();
                }
                else if (id.length() < 8 || id.length() > 16){
                    Toast.makeText(Register.this, "Length of username should be 8-16.",Toast.LENGTH_SHORT).show();

                }
                else if (pw.length() < 8 || pw.length() > 16){
                    Toast.makeText(Register.this, "Length of password should be 8-16.",Toast.LENGTH_SHORT).show();

                }
                else if (!email1.contains("@")||!email1.endsWith(".com")){
                    Toast.makeText(Register.this, "Please register with a proper email.",Toast.LENGTH_SHORT).show();

                }
                else if (phoneNo.length() < 10 || phoneNo.length() > 11){
                    Toast.makeText(Register.this, "Please register with a proper phone number.",Toast.LENGTH_SHORT).show();

                }
                else if (address1.length() < 25){
                    Toast.makeText(Register.this, "Address is too short.",Toast.LENGTH_SHORT).show();

                }
                else{
                    if(pw.equals(pw2)){
                        Boolean existUser = DB.checkusername(id);
                        if(!existUser){
                            Boolean insertSuccess = DB.insertBuyer(id,pw,email1,phoneNo,address1);
                            if(insertSuccess){
                                Toast.makeText(Register.this, "Registered Succesfully!",Toast.LENGTH_SHORT).show();
                                backToLogin();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "User Exist!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Register.this, "Passwords not matching",Toast.LENGTH_SHORT).show();
                    }

                }
                registerAccount();
            }
        });
    }
    public void backToLogin() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void registerAccount(){

    }
}