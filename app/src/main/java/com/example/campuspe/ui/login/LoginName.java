package com.example.campuspe.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.campuspe.R;
import com.example.campuspe.ui.LoggedIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginName extends AppCompatActivity {

    EditText name,reg;
    Button verBtn;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_name);
        name = findViewById(R.id.editName);
        verBtn = findViewById(R.id.verBtn);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("UserInfo");
mAuth=FirebaseAuth.getInstance();
reg=findViewById(R.id.editReg);

        verBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String namee = name.getText().toString();
                String phone = mAuth.getCurrentUser().getPhoneNumber();
                String RegNo = reg.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(namee)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(LoginName.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(namee,phone,RegNo);
                }
            }
        });
    }

    private void addDatatoFirebase(String name,String phone,String reg) {
        // below 3 lines of code is used to set
        // data in our object class.

        user = new User(name,phone,reg);
        // we are use add value event listener method
        // which is called with database reference.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase
                String uid=mAuth.getCurrentUser().getUid();
                myRef.child(uid).setValue(user);
                // after adding this data we are showing toast message.

                Toast.makeText(LoginName.this, "Data Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), LoggedIn.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(LoginName.this, "Failed to add data " + error, Toast.LENGTH_SHORT).show();

            }
        });
    }
}

