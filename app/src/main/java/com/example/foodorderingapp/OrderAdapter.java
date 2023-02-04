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

public class OrderAdapter extends RecyclerView.Adapter<orderVH>{

    //List<String> items;
    List<Order> orders;
    Context context;


    public OrderAdapter(List<Order> orders){
        this.orders = orders;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public orderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ongoing_orderslayout, parent,false);
        return new orderVH(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull orderVH holder, int position) {
        //holder.OrderID.setText((orders.get(position).getOrderID()));
        //holder.foodName.setText((orders.get(position).getMyCart().toName()));
        //holder.foodName.setText("999");
        //holder.price.setText("999");
        //holder.price.setText((Double.toString(orders.get(position).getMyCart().calculatePrice())));
        //holder.status.setText("Ongoing");
        //holder.status.setText((orders.get(position).getOrderStatus()));
        //holder.phone.setText(("0123456789"));
        //TODO: implement the deliveryguydatabase and deliveryguy objectinordertogenerate phone


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}

class orderVH extends RecyclerView.ViewHolder{

    TextView OrderID,foodName,price,status,ETA,phone;
    private OrderAdapter adapter;

    public orderVH(@NonNull View itemView) {
        super(itemView);

        OrderID = itemView.findViewById(R.id.orderID);
        foodName = itemView.findViewById(R.id.foodName);
        price = itemView.findViewById(R.id.price);
        status = itemView.findViewById(R.id.status);
        ETA = itemView.findViewById(R.id.ETA);
        phone = itemView.findViewById(R.id.phone);

        itemView.findViewById(R.id.cancel).setOnClickListener(view -> {
            //TODO: ADD THE CANCELLATION FUNCTION
            //ManageFoodmain.cart.add(adapter.food.get(getAdapterPosition()));
            //Toast.makeText(adapter.context, "Added Successfully!",Toast.LENGTH_SHORT).show();
        });
    }

    public orderVH linkAdapter(OrderAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}