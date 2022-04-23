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

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder>{
    Context contextHere;
    ArrayList<CouponDetails> couponList;

    public CouponAdapter(Context contextHere, ArrayList<CouponDetails> couponList) {
        this.contextHere = contextHere;
        this.couponList = couponList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextHere).inflate(R.layout.item_coupon,parent,false);
        return new CouponAdapter.ViewHolder(view);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CouponDetails data = couponList.get(position);
        holder.coupon_code.setText("Code : "+data.getCode());
        holder.coupon_det.setText(""+data.getDesc());
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView coupon_code;
        TextView coupon_det;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coupon_code = itemView.findViewById(R.id.couponName);
            coupon_det = itemView.findViewById(R.id.couponDesc);
        }
    }
}
