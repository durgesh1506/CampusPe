package com.example.campuspe.ui.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campuspe.R;
import com.example.campuspe.ui.LoggedIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    Button otpBtn,verifyBtn;
    EditText tv,codeVer;
    FirebaseAuth mAuth;
    String phoneNo;
    ProgressBar progressBar,progressBar1;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        otpBtn = findViewById(R.id.otpbtn);
        tv=findViewById(R.id.username);
        mAuth = FirebaseAuth.getInstance();
        progressBar1=findViewById(R.id.progressBar);



        //otpBtn.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {
          //      phoneNo = tv.getText().toString();

           //     registerUser(v);
           //    sendVerificationCodeToUser(phoneNo);
           //     showDialog();
           // }

       // });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent=new Intent(getApplicationContext(),LoggedIn.class);
            startActivity(intent);
        }
    }

    private Boolean validatePhoneNo() {
        String val = tv.getText().toString();

        if (val.isEmpty()) {
            tv.setError("Field cannot be empty");
            return false;
        } else {
            tv.setError(null);

            return true;
        }
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        private static final String TAG ="";

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            String code = credential.getSmsCode();
            codeVer.setText(code);
            progressBar1.setVisibility(View.VISIBLE);

            if (code != null) {
                verifyCode(code);
            }
            Toast.makeText(getApplicationContext(),"Verification Completed",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Log.w(TAG, "onVerificationFailed", e);

          Toast.makeText(getApplicationContext(),"Code Was Not Sent",Toast.LENGTH_SHORT).show();

            // Show a message and update the UI
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided mobile number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Toast.makeText(getApplicationContext(),"Verification Code Sent Successfully",Toast.LENGTH_SHORT).show();
            mVerificationId = verificationId;
            mResendToken = token;


        }
    };

    public void registerUser(View view) {

        // Performing Validation by calling validation functions
        if (!validatePhoneNo()) {
            return;
        }

        //Get the Phone No from phone no field in String


    }

    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, codeByUser);
        signInTheUserByCredentials(credential);

    }




    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();
                    long creationTimestamp = Objects.requireNonNull(user.getMetadata()).getCreationTimestamp();
                    long lastSignInTimestamp = user.getMetadata().getLastSignInTimestamp();
                    if (creationTimestamp == lastSignInTimestamp) {
                        progressBar1.setVisibility(View.INVISIBLE);


                        Toast.makeText(getApplicationContext(), "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),LoginName.class);
                        startActivity(intent);
                    } else {
                        progressBar1.setVisibility(View.INVISIBLE);

                        Toast.makeText(getApplicationContext(), "Logged In successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),LoggedIn.class);
                        startActivity(intent);
                    }
                } else {
                    // Sign in failed, display a message and update the UI-
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getApplicationContext(), "Logged In Failed", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        progressBar1.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.otp_dialog);
        verifyBtn=dialog.findViewById(R.id.button2);
        codeVer=dialog.findViewById(R.id.editTextNumber2);
        sendVerificationCodeToUser(phoneNo);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verCode= codeVer.getText().toString();
                String code = verCode;


                    progressBar1.setVisibility(View.VISIBLE);

                if (code.isEmpty() || code.length() < 6) {
                    codeVer.setError("Wrong OTP...");
                    codeVer.requestFocus();
                    progressBar1.setVisibility(View.INVISIBLE);

                    return;
                }

                verifyCode(code);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

}
