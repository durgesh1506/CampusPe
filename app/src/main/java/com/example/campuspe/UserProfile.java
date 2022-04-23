package com.example.campuspe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campuspe.ui.LoggedIn;
import com.example.campuspe.ui.login.LoginActivity;
import com.example.campuspe.ui.login.LoginName;
import com.example.campuspe.ui.login.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserProfile extends AppCompatActivity {
    Button logout;

    TextView perName, mail, phone, edit, save;
    FirebaseDatabase database;
    EditText editPerName;
    DatabaseReference databaseReference;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    String uid,namee,num,name,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        logout = findViewById(R.id.logout);
        uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        database = FirebaseDatabase.getInstance();
        edit = findViewById(R.id.editName);
        perName = findViewById(R.id.textName);
        mail = findViewById(R.id.textEmail);
        phone = findViewById(R.id.textPhone);
        editPerName = findViewById(R.id.editPerName);
        save = findViewById(R.id.editName2);
      database = FirebaseDatabase.getInstance();
        myRef = database.getReference("UserInfo").child(uid);
        mAuth=FirebaseAuth.getInstance();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                name = user.username;
                num = user.phone;
                reg = user.regNo;
                perName.setText(name);
                phone.setText(num);
                mail.setText(reg);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Something Wrong Happened", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               edit.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);
                perName.setVisibility(View.INVISIBLE);
                editPerName.setVisibility(View.VISIBLE);


                save.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        namee = editPerName.getText().toString();

                        if (TextUtils.isEmpty(namee)) {
                            Toast.makeText(getApplicationContext(), "Please add some data.", Toast.LENGTH_SHORT).show();
                        } else {
                            addDatatoFirebase(namee,num,reg);
                        }
                    }
                });


            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

            }
        });

    }



    private void addDatatoFirebase(String name,String pn,String reg) {
       User user = new User(name,pn,reg);
        myRef.setValue(user);
        edit.setVisibility(View.VISIBLE);
        save.setVisibility(View.INVISIBLE);
        perName.setVisibility(View.VISIBLE);
        editPerName.setVisibility(View.INVISIBLE);

    }
}


