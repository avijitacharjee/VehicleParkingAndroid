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
            startActivity(new Intent(getApplicationContext(),ActivityProfile.class));
        });
        binding.map.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MapsActivity.class));
        });
        binding.Booking.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),BookingActivity.class));
        });
        binding.help.setOnClickListener(v->{
            String phone = "+34666777888";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        });
        binding.emergency.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
        binding.History.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
        });

    }
}