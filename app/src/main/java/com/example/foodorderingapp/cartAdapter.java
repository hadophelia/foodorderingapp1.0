package com.example.foodorderingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartVH>{

    List<String> items;

    public cartAdapter(List<String> items){
        this.items = items;
    }

    @NonNull
    @Override
    public cartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartlayout, parent,false);
        return new cartVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull cartVH holder, int position) {
        holder.textView.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class cartVH extends RecyclerView.ViewHolder{

    TextView textView;
    private cartAdapter adapter;

    public cartVH(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.foodName);
        itemView.findViewById(R.id.delete).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public cartVH linkAdapter(cartAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}