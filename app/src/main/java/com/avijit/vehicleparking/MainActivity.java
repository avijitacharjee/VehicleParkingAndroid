package com.avijit.vehicleparking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avijit.vehicleparking.databinding.ActivityMainBinding;
import com.google.zxing.qrcode.encoder.QRCode;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding ;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.primaryColor));

        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        /*ImageView img = findViewById(R.id.image);
        Picasso.get().load("https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=Aviji").into(img);*/
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        binding.goBtn.setOnClickListener(v->{
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            String url = "https://www.finalproject.xyz/vehicle_parking/api/auth.php";
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if(response.contains("success")){
                        Log.d(TAG, "onResponse: "+response);
                        getSharedPreferences("s",MODE_PRIVATE).edit().putString("user",response).apply();
                        Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MapsActivity.class));

                    }else {
                        Toast.makeText(MainActivity.this, "Invalid email/password", Toast.LENGTH_SHORT).show();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return super.getHeaders();
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("email",binding.emailEditText.getText().toString());
                    params.put("password",binding.passwordEditText.getText().toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        });
        binding.signupIntentButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),ActivitySignUp.class));
        });
        ObjectAnimator.ofFloat(binding.mainLayout, View.TRANSLATION_Y,1500f,0).setDuration(1000).start();
        ObjectAnimator.ofFloat(binding.welcomeText,View.ALPHA,0,1).setDuration(2000).start();
        //ObjectAnimator.ofFloat(binding.ll,View.ALPHA,0,1).setDuration(2000).start();
        ObjectAnimator.ofFloat(binding.text2,View.SCALE_X,0,1f).setDuration(1000).start();
        ObjectAnimator.ofFloat(binding.text2,View.SCALE_Y,0,1f).setDuration(1000).start();

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
}