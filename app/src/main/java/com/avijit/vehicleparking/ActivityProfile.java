package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.avijit.vehicleparking.databinding.ActivityProfileBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ActivityProfile extends AppCompatActivity {
    private static final String TAG = "ActivityProfile";
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Picasso.get().load("https://cdn.iconscout.com/icon/free/png-256/avatar-370-456322.png").into(binding.imageView);
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
        binding.nameTextView.setText("Name: "+name);
        binding.email.setText("Email: "+email);
        binding.phone.setText("Phone: "+phone);
    }
}