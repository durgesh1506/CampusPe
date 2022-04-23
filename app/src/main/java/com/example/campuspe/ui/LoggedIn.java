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

import static java.security.AccessController.getContext;

public class LoggedIn extends AppCompatActivity {
    int backCnt=0;
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    ArrayList<CanteenData> canteenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        CircleImageView prof = view.findViewById(R.id.profile_image);
        canteenList = new ArrayList<>();
        canteenList.add(new CanteenData("Yashnath"));
        canteenList.add(new CanteenData("Raj"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));
        canteenList.add(new CanteenData("Talwar"));


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        listAdapter = new ListAdapter(getApplicationContext(),canteenList);
        recyclerView.setAdapter(listAdapter);
    }


}