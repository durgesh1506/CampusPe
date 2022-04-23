package com.example.campuspe.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.campuspe.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RewardActivity extends AppCompatActivity {
    TextView actionName;
    RecyclerView recyclerView;
    ArrayList<CouponDetails> couponList;
    CouponAdapter couponAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.canteen_action_bar);
        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        actionName = view.findViewById(R.id.actionName);
        actionName.setText("Rewards");
        couponList = new ArrayList<>();
        recyclerView = findViewById(R.id.couponRecycler);
        
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));
        couponList.add(new CouponDetails("panda","cibeclcajcac",100));

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        couponAdapter = new CouponAdapter(getApplicationContext(),couponList);
        recyclerView.setAdapter(couponAdapter);
    }
}