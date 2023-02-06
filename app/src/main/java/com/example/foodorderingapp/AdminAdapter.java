package com.example.foodorderingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<adminVH>{

    //List<String> items;
    List<Order> orders;
    Context context;
    SystemDB DB;



    public AdminAdapter(List<Order> orders){
        this.orders = orders;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public adminVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adminlayout, parent,false);
        return new adminVH(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull adminVH holder, int position) {
        Order tempOrder = orders.get(position);
        holder.OrderID.setText(("Order Number: #" + Integer.toString(orders.get(position).getOrderID())));
        holder.foodName.setText(("Food Ordered: " + orders.get(position).getFoodID()));
        holder.price.setText(("Price: RM" + Double.toString(tempOrder.getPrice())));
        holder.status.setText(("Order Status: " + tempOrder.getOrderStatus()));
        DB = new SystemDB(context);
        holder.Bphone.setText("Buyer Phone Number: " + DB.getBuyerPhone(tempOrder.getBuyerID()));
        if(tempOrder.getDeliveryGuyID()!=null){
            holder.DGphone.setText("Delivery Guy Phone Number: " + DB.getDGphoneNumber(tempOrder.getDeliveryGuyID()));
        }
        if (tempOrder.getETA()!=null){
            holder.ETA.setText("ETA: " + tempOrder.getETA());
        }



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}

class adminVH extends RecyclerView.ViewHolder{

    TextView OrderID,foodName,price,status,ETA,DGphone,Bphone;
    private AdminAdapter adapter;

    public adminVH(@NonNull View itemView) {
        super(itemView);

        OrderID = itemView.findViewById(R.id.orderID);
        foodName = itemView.findViewById(R.id.FoodName);
        price = itemView.findViewById(R.id.price);
        status = itemView.findViewById(R.id.status);
        ETA = itemView.findViewById(R.id.ETA);
        DGphone = itemView.findViewById(R.id.DGphone);
        Bphone = itemView.findViewById(R.id.Bphone);

    }

    public adminVH linkAdapter(AdminAdapter adapter){
        this.adapter = adapter;
        return this;
    }

}