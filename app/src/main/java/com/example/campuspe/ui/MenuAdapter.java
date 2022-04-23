package com.example.campuspe.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.widget.Filter;

import com.example.campuspe.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{
    Context contextHere;
    ArrayList<FoodDetails> foodList;


    public MenuAdapter(Context contextHere, ArrayList<FoodDetails> food) {
        this.contextHere = contextHere;
        this.foodList = food;
        //filteredNameList = new ArrayList<>(complexList);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextHere).inflate(R.layout.item_food_list,parent,false);
//        Toast.makeText(contextHere, "oncreate called", Toast.LENGTH_SHORT).show();
        return new MenuAdapter.ViewHolder(view);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDetails data = foodList.get(position);
//        Log.d("chatTag", "onBindViewHolder: "+user.getName());
        holder.food_name.setText(data.getFoodName());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView food_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name = itemView.findViewById(R.id.foodName);
        }
    }
}
