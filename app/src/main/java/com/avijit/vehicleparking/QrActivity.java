package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avijit.vehicleparking.databinding.ActivityQrBinding;
import com.squareup.picasso.Picasso;

public class QrActivity extends AppCompatActivity {
    ActivityQrBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Picasso.get().load("https://chart.googleapis.com/chart?chs=100x100&cht=qr&chl=Aviji").into(binding.imageView);
    }
}