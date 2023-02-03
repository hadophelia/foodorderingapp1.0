package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login,register;
    private EditText username,password;
    SystemDB DB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
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

                if (id.equals("")||pw.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter username and password",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean validuser = DB.validateBuyer(id,pw);
                    if (validuser){
                        Toast.makeText(MainActivity.this, "Signed in Succesful!",Toast.LENGTH_SHORT).show();
                        openManage();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credential!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
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