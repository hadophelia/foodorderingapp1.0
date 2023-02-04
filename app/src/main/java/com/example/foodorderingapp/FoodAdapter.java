package com.example.foodorderingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<foodVH>{

    //List<String> items;
    List<Food> food;
    Context context;

    //public FoodAdapter(List<String> items){this.items = items;}
    public FoodAdapter(List<Food> foods){
        this.food = foods;
    }

    public void setContext(Context context){
        this.context = context;
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
        holder.textView.setText(food.get(position).getName());
        holder.price.setText("RM" + Double.toString(food.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return food.size();
    }

}

class foodVH extends RecyclerView.ViewHolder{

    TextView textView,price;
    private FoodAdapter adapter;

    public foodVH(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.foodName);
        price = itemView.findViewById(R.id.price);
        itemView.findViewById(R.id.add).setOnClickListener(view -> {

            ManageFoodmain.cart.add(adapter.food.get(getAdapterPosition()));
            Toast.makeText(adapter.context, "Added Successfully!",Toast.LENGTH_SHORT).show();
        });
    }

    public foodVH linkAdapter(FoodAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
