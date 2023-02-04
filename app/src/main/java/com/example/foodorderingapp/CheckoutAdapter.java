package com.example.foodorderingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<checkoutVH>{

    List<Food> items;

    public CheckoutAdapter(List<Food> items){
        this.items = items;
    }

    @NonNull
    @Override
    public checkoutVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkoutlayout, parent,false);
        return new checkoutVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull checkoutVH holder, int position) {
        holder.textView.setText(items.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class checkoutVH extends RecyclerView.ViewHolder{

    TextView textView;
    private CheckoutAdapter adapter;

    public checkoutVH(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.foodName);
    }

    public checkoutVH linkAdapter(CheckoutAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}