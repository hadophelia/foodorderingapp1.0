package com.example.foodorderingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemDB extends SQLiteOpenHelper {

    public static final String DBNAME = "FoodOrdering.db";

    public SystemDB(Context context) {
        super(context, "FoodOrdering.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table buyers(username TEXT primary key, password TEXT,email TEXT,phoneNumber TEXT,address TEXT)");
        MyDB.execSQL("create Table foods(foodID TEXT primary key,foodName TEXT,foodDescription TEXT,foodPrice double)");
        MyDB.execSQL("create Table deliveryGuys(deliveryGuyID TEXT primary key,username TEXT, password TEXT,phoneNumber TEXT,plateNumber TEXT)");
        MyDB.execSQL("create Table orders(orderID INTEGER primary key autoincrement,buyerUsername TEXT,address TEXT,FoodID TEXT,orderStatus TEXT,deliveryGuyID TEXT, ETA TEXT,Price double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists foods");
        MyDB.execSQL("drop table if exists buyers");
        MyDB.execSQL("drop table if exists deliveryGuys");
        MyDB.execSQL("drop table if exists orders");
    }

    public void clearOrders(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("drop table if exists orders");
        MyDB.execSQL("create Table orders(orderID INTEGER primary key autoincrement,buyerUsername TEXT,address TEXT,FoodID TEXT,orderStatus TEXT,deliveryGuyID TEXT, ETA TEXT,Price double)");

    }

    public String getDGphoneNumber(String dgID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String phone = null;

        Cursor cursor = (MyDB.rawQuery("Select phoneNumber from deliveryGuys where deliveryGuyID = ?", new String[]{dgID}));
        cursor.moveToFirst();
        if (cursor != null){
            if (cursor.moveToFirst()){
                phone = cursor.getString(cursor.getColumnIndex("address"));
            }
            //the warning is dealed with cursor.movetofirst()
        }
        return phone;
    }

    /*public Order getOrder(String orderID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Order myOrder = null;
        Cursor cursor = MyDB.rawQuery("Select * from orders where orderID = ?", new String[]{orderID});
        cursor.moveToFirst();
        if(cursor.moveToFirst()){

            int orderNo = cursor.getInt(cursor.getColumnIndex("orderID"));
            String buyerID = cursor.getString(cursor.getColumnIndex("buyerUsername"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String foodID = cursor.getString(cursor.getColumnIndex("FoodID"));
            String orderStatus = cursor.getString(cursor.getColumnIndex("orderStatus"));
            String deliveryGuyID = cursor.getString(cursor.getColumnIndex("deliveryGuyID"));
            String ETA = cursor.getString(cursor.getColumnIndex("ETA"));
            myOrder = new Order(orderNo, buyerID,address,foodID,orderStatus,deliveryGuyID,ETA);
        }

        return myOrder;
    }*/


    public ArrayList<Food> getFood(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from foods" ,null);
        ArrayList<Food> resultList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Food food = new Food(cursor.getString(0), cursor.getString(1),cursor.getString(2), cursor.getDouble(3));
                resultList.add(food);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return resultList;
    }

    public ArrayList<Order> getKitchenOrder(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from orders where orderStatus = ?", new String[]{"Order Received!"});
        ArrayList<Order> resultList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Order order = new Order(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)
                        , cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getDouble(7));
                resultList.add(order);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return resultList;
    }


    public ArrayList<Order> getBuyerOrder(String buyerID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from orders where buyerUsername = ?", new String[]{buyerID});
        ArrayList<Order> resultList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Order order = new Order(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)
                        , cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getDouble(7));
                resultList.add(order);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return resultList;
    }




    public void requestDelivery(int orderID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        //String[] parameter = {"Ready to be delivered.",Integer.toString(orderID)};
        MyDB.execSQL("UPDATE orders SET orderStatus = 'Readey to be delivered.' WHERE orderID = " + orderID);
    }

    public void acceptingDelivery(int orderID, String DeliveryGuyID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String[] parameter = {DeliveryGuyID,"Delivering",Integer.toString(orderID)};
        MyDB.rawQuery("UPDATE orders SET deliveryGuyID = ?,status = ? WHERE orderID = ?", parameter);

    }

    public void setETA(int orderID,String ETA){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String[] parameter = {ETA,Integer.toString(orderID)};
        MyDB.rawQuery("UPDATE orders SET ETA = ? WHERE orderID = ?", parameter);
    }

    public boolean createOrder(String buyerUsername,String address,String FoodID,Double price){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("buyerUsername", buyerUsername); // column name,value
        contentValues.put("address", address);
        contentValues.put("FoodID", FoodID);
        contentValues.put("orderStatus", "Order Received!");
        contentValues.put("Price",price);
        long result = MyDB.insert("orders", null, contentValues);
        if(result==-1) return false; //insertion failed
        else
            return true;
    }

    public boolean insertDeliveryGuy(String deliveryGuyID, String username, String password, String phoneNumber, String plateNumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("deliveryGuyID", deliveryGuyID); // column name,value
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("plateNumber", plateNumber);
        long result = MyDB.insert("deliveryGuys", null, contentValues);
        if(result==-1) return false; //insertion failed
        else
            return true;
    }

    public boolean insertFood(String foodID, String foodName, String foodDescription, double foodPrice){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("foodID", foodID); // column name,value
        contentValues.put("foodName", foodName);
        contentValues.put("foodDescription", foodDescription);
        contentValues.put("foodPrice", foodPrice);
        long result = MyDB.insert("foods", null, contentValues);
        if(result==-1) return false; //insertion failed
        else
            return true;
    }

    public boolean insertBuyer(String username,String password,String email,String phoneNumber,String address){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username); // column name,value
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("address", address);
        long result = MyDB.insert("buyers", null, contentValues);
        if(result==-1) return false; //insertion failed
        else
            return true;
    }

    public Boolean checkusername(String username) { //to check if a username is present in the system
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from buyers where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public String getAddress(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String address = null;

        Cursor cursor = (MyDB.rawQuery("Select address from buyers where username = ?", new String[]{username}));
        cursor.moveToFirst();
        if (cursor != null){
            cursor.moveToFirst();
            address = cursor.getString(cursor.getColumnIndex("address")); //the warning is dealed with cursor.movetofirst()
        }
        return address;
    }


    public Boolean validateBuyer(String username, String password){ //check if a user exist
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from buyers where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

