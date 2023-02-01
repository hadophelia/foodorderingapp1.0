package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {

    public static final String DBNAME = "FoodOrdering.db";

    public UserDB(Context context) {
        super(context, "FoodOrdering.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT,email TEXT,phoneNumber TEXT,address TEXT)"); //create user table with two attributes
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");
    }

    public boolean insertData(String username,String password,String email,String phoneNumber,String address){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username); // column name,value
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("address", address);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false; //insertion failed
        else
            return true;
    }

    public Boolean checkusername(String username) { //to check if a username is present in the system
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean validateUser(String username, String password){ //check if a user exist
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

