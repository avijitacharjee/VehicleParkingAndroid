package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.avijit.vehicleparking.databinding.ActivityMainDashboardBinding;

public class MainDashboard extends AppCompatActivity {
    ActivityMainDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.profile.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),ActivityProfile.class));
        });
        binding.map.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MapsActivity.class));
        });

    }
}