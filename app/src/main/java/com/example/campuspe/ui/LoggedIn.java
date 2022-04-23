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
import android.widget.TextView;

import com.example.campuspe.R;
import com.example.campuspe.UserProfile;
import com.example.campuspe.ui.login.LoginActivity;

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

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        listAdapter = new ListAdapter(getApplicationContext(),canteenList);
        recyclerView.setAdapter(listAdapter);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

    }

    }

