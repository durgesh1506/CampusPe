package com.example.campuspe.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.campuspe.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CanteenActivity extends AppCompatActivity {
    TextView actionName, fare;
    RecyclerView recyclerView;
    MenuAdapter menuAdapter;
    ArrayList<FoodDetails> foodList;
    Button payBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.canteen_action_bar);
        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        CircleImageView back = view.findViewById(R.id.actionBack);
        actionName = view.findViewById(R.id.actionName);
        String canteenName = getIntent().getStringExtra("cName");
        actionName.setText(canteenName);
        payBtn = findViewById(R.id.payBtn);
        
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        foodList = new ArrayList<>();
        recyclerView = findViewById(R.id.menuRecycler);

        fare = findViewById(R.id.fare);


        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));
        foodList.add(new FoodDetails("khana",200));

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                intent.putExtra("canteen_name",canteenName);
                intent.putExtra("total_fare",Integer.parseInt(fare.getText().toString().substring(3)));
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        menuAdapter = new MenuAdapter(getApplicationContext(),foodList,fare);
        recyclerView.setAdapter(menuAdapter);
    }

}