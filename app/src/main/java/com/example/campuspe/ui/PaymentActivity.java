package com.example.campuspe.ui;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.Objects;

//implements payment feature

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    int fare,d,m,y,hr1,hr2;
    String complexName,userPhone,userEmail,canteenName,uid,sportPrice,userName;
    FirebaseAuth fauth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());
        fare = getIntent().getIntExtra("total_fare",0);
//        uid = getIntent().getStringExtra("uid");
        canteenName = getIntent().getStringExtra("canteen_name");

        fauth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        startPayment();
    }

    //Func for initiating portal
    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_6bRRkpCfQh2t0B");

//        checkout.setImage(R.drawable.logo);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", canteenName);
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", fare*100);//pass amount in currency subunits
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPaymentSuccess(String s) {
//        Toast.makeText(this, "Successful" + s, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),PaymentStatus.class);
        intent.putExtra("status","Payment Successful");
        intent.putExtra("payId",s);

        startActivity(intent);
    }

    @Override
    public void onPaymentError(int i, String s) {
//        Toast.makeText(this, "Error cause by" + s, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),PaymentStatus.class);
        intent.putExtra("status","Payment Failed");
        intent.putExtra("payId",s);
        startActivity(intent);
    }
}