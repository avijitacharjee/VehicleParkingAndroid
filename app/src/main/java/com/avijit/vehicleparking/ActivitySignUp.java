package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avijit.vehicleparking.databinding.ActivitySignUpBinding;

public class ActivitySignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivitySignUpBinding.inflate(getLayoutInflater()).getRoot());
    }
}