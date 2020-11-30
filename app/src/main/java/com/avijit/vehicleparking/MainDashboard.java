package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
        });
        binding.map.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MapsActivity.class));
        });
        binding.Booking.setOnClickListener(v->{
        });
        binding.help.setOnClickListener(v->{

        });
        binding.emergency.setOnClickListener(v->startActivity(new Intent(getApplicationContext(),MainActivity.class)));
        binding.History.setOnClickListener(v->{
        });

    }
}