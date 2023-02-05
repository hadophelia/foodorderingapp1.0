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

public class KitchenAdapter extends RecyclerView.Adapter<kitchenVH>{

    List<Order> orders;
    static Context context;
    static SystemDB DB;


    public KitchenAdapter(List<Order> orders){
        this.orders = orders;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public kitchenVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kitchen_orderlayout, parent,false);
        return new kitchenVH(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull kitchenVH holder, int position) {
        Order tempOrder = orders.get(position);
        holder.OrderID.setText(("Order Number: #" + Integer.toString(orders.get(position).getOrderID())));
        //holder.foodName.setText("Food Ordered");
        holder.foodName.setText(("Food Ordered: " + orders.get(position).getFoodID()));
        //holder.phone.setText(("0123456789"));
        //TODO: implement the deliveryguydatabase and deliveryguy objectinordertogenerate phone


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}

class kitchenVH extends RecyclerView.ViewHolder{

    TextView OrderID,foodName;
    private KitchenAdapter adapter;

    public kitchenVH(@NonNull View itemView) {
        super(itemView);

        OrderID = itemView.findViewById(R.id.orderID);
        foodName = itemView.findViewById(R.id.FoodName);


        itemView.findViewById(R.id.requestDelivery).setOnClickListener(view -> {
            int orderID = adapter.orders.get(getAdapterPosition()).getOrderID();
            adapter.DB = new SystemDB(adapter.context);
            adapter.DB.requestDelivery(orderID);
            adapter.orders.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());

            //TODO: ADD THE CANCELLATION FUNCTION
            Toast.makeText(adapter.context, "Notifying Delivery Guy!",Toast.LENGTH_SHORT).show();
        });
    }

    public kitchenVH linkAdapter(KitchenAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
