package com.example.foodorderingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<foodVH>{

    List<String> items;

    public FoodAdapter(List<String> items){
        this.items = items;
    }

    @NonNull
    @Override
    public foodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foodlayout, parent,false);
        return new foodVH(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull foodVH holder, int position) {
        holder.textView.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

class foodVH extends RecyclerView.ViewHolder{

    TextView textView;
    private FoodAdapter adapter;

    public foodVH(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.foodName);
        itemView.findViewById(R.id.add).setOnClickListener(view -> {

            ManageFoodmain.cart.add(adapter.items.get(getAdapterPosition()));
        });
    }

    public foodVH linkAdapter(FoodAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
