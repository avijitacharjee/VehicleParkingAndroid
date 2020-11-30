package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.avijit.vehicleparking.databinding.ActivityBookingBinding;

import org.json.JSONObject;

public class BookingActivity extends AppCompatActivity {
    private static final String TAG = "BookingActivity";
    boolean booked;
    ActivityBookingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String name="";
        String email="";
        String phone = "";
        try {

            JSONObject user = new JSONObject(getSharedPreferences("s",MODE_PRIVATE).getString("user",""));
            name = user.getJSONObject("data").getString("name");
            email = user.getJSONObject("data").getString("email");
            phone = user.getJSONObject("data").getString("phone");
        }catch (Exception e){
            Log.d(TAG, "onCreate: "+e.toString());
        }
        binding.text.setOnClickListener(v->{
            if(booked) {
                startActivity(new Intent(getApplicationContext(), QrActivity.class));
            }else {
                booked=true;
                binding.text.setText("You have booked for a slot. Click to see qr code.");
            }
        });
    }
}