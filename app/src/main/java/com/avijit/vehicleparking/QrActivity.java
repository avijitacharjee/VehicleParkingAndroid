package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avijit.vehicleparking.databinding.ActivityQrBinding;
import com.squareup.picasso.Picasso;

public class QrActivity extends AppCompatActivity {
    ActivityQrBinding binding;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        token = getSharedPreferences("s",MODE_PRIVATE).getString("token","abcd");
        Picasso.get().load("https://chart.googleapis.com/chart?chs=100x100&cht=qr&chl="+token).into(binding.imageView);
    }
}