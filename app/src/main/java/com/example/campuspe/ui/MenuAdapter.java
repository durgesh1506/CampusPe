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

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{
    Context contextHere;
    ArrayList<FoodDetails> foodList;
    View view;
    int totalfare = 0;

    public MenuAdapter(Context contextHere, ArrayList<FoodDetails> food, View view) {
        this.contextHere = contextHere;
        this.foodList = food;
        this.view = view;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextHere).inflate(R.layout.item_food_list,parent,false);
        return new MenuAdapter.ViewHolder(view);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDetails data = foodList.get(position);
        holder.food_name.setText(data.getFoodName());
        holder.food_price.setText("Rate : "+data.getPrice());
        TextView fare = view.findViewById(R.id.fare);
        fare.setText("Rs."+totalfare);
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.minnteger= holder.minnteger+1;
                holder.integer_number.setText(""+holder.minnteger);
                totalfare+=data.getPrice();
                fare.setText("Rs."+totalfare);
            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.minnteger>0 && totalfare>=data.getPrice()) {
                    holder.minnteger = holder.minnteger - 1;
                    holder.integer_number.setText("" + holder.minnteger);
                    totalfare-=data.getPrice();
                    fare.setText("Rs."+totalfare);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public int getTotalfare(){return totalfare;}

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView food_name;
        TextView food_price;
        CircleImageView increase;
        CircleImageView decrease;
        TextView integer_number;
        int minnteger = 0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name = itemView.findViewById(R.id.foodName);
            increase = itemView.findViewById(R.id.increase);
            decrease = itemView.findViewById(R.id.decrease);
            integer_number = itemView.findViewById(R.id.integer_number);
            food_price = itemView.findViewById(R.id.foodprice);
        }
    }
}
