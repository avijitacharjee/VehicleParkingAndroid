package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.avijit.vehicleparking.databinding.ActivityMainBinding;
import com.google.zxing.qrcode.encoder.QRCode;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivityMainBinding.inflate(getLayoutInflater()).getRoot());
        /*ImageView img = findViewById(R.id.image);
        Picasso.get().load("https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=Aviji").into(img);*/

    }
}