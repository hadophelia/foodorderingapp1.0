package com.example.foodorderingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<deliveryVH>{

    List<Order> orders;
    static Context context;
    static SystemDB DB;


    public DeliveryAdapter(List<Order> orders){
        this.orders = orders;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public deliveryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deliveryguylayout, parent,false);
        return new deliveryVH(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull deliveryVH holder, int position) {
        Order tempOrder = orders.get(position);
        holder.OrderID.setText(("Order Number: #" + Integer.toString(orders.get(position).getOrderID())));
        //holder.foodName.setText("Food Ordered");
        holder.foodName.setText(("Food Ordered: " + orders.get(position).getFoodID()));
        holder.address.setText("Address : " + orders.get(position).getAddress());
        //holder.phone.setText(("0123456789"));



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}

class deliveryVH extends RecyclerView.ViewHolder{

    TextView OrderID,foodName,address;
    private DeliveryAdapter adapter;

    public deliveryVH(@NonNull View itemView) {
        super(itemView);

        OrderID = itemView.findViewById(R.id.orderID);
        foodName = itemView.findViewById(R.id.FoodName);
        address = itemView.findViewById(R.id.Address);


        itemView.findViewById(R.id.acceptDelivery).setOnClickListener(view -> {
            if(DeliveryGuy_Home.currentOrder!=null){
                Toast.makeText(adapter.context, "You have an ongoing task!",Toast.LENGTH_SHORT).show();
            }
            else{
                int orderID = adapter.orders.get(getAdapterPosition()).getOrderID();
                adapter.DB = new SystemDB(adapter.context);
                adapter.DB.acceptingDelivery(adapter.orders.get(getAdapterPosition()).getOrderID(),DeliveryGuy_Home.currentDG);
                adapter.orders.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
                Toast.makeText(adapter.context, "Order Accepted!",Toast.LENGTH_SHORT).show();
            }


        });
    }

    public deliveryVH linkAdapter(DeliveryAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
