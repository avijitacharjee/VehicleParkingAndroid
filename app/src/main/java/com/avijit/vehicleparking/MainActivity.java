package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.avijit.vehicleparking.databinding.ActivityMainBinding;
import com.google.zxing.qrcode.encoder.QRCode;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*ImageView img = findViewById(R.id.image);
        Picasso.get().load("https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=Aviji").into(img);*/
        binding.goBtn.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MainDashboard.class));
        });
        binding.signupIntentButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),ActivitySignUp.class));
        });
    }
}