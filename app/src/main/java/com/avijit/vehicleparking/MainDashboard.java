package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avijit.vehicleparking.databinding.ActivityMainDashboardBinding;

public class MainDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivityMainDashboardBinding.inflate(getLayoutInflater()).getRoot());
    }
}