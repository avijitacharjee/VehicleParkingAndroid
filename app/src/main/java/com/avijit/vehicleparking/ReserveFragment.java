package com.avijit.vehicleparking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.avijit.vehicleparking.databinding.ActivityBookingBinding;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 3:17 AM.
 * Email: avijitach@gmail.com.
 */
public class ReserveFragment extends Fragment {
    private static final String TAG = "ReserveFragment";
    boolean booked;
    ActivityBookingBinding binding;
    String mask="";
    String userId;
    long token;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityBookingBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String name="";
        String email="";
        String phone = "";
        try {
            JSONObject user = new JSONObject(getActivity().getSharedPreferences("s",MODE_PRIVATE).getString("user",""));
            name = user.getJSONObject("data").getString("name");
            userId = user.getJSONObject("data").getString("id");
            email = user.getJSONObject("data").getString("email");
            phone = user.getJSONObject("data").getString("phone");
            mask = user.getString("mask");
        }catch (Exception e){
            Log.d(TAG, "onCreate: "+e.toString());
        }
        char A[] = mask.toCharArray();
        if(A[0]=='0'){
            binding.a3.setBackgroundColor(Color.RED);
        }
        if(A[1]=='0'){
                    binding.a2.setBackgroundColor(Color.RED);
                }
        if(A[2]=='0'){
                    binding.a1.setBackgroundColor(Color.RED);
                }
        if(A[3]=='0'){
                    binding.b3.setBackgroundColor(Color.RED);
                }
        if(A[4]=='0'){
                    binding.b2.setBackgroundColor(Color.RED);
                }
        if(A[5]=='0'){
                    binding.b1.setBackgroundColor(Color.RED);
                }
        CountDownTimer timer = new CountDownTimer(20*60*1000,1000) {
            @Override
            public void onTick(long l) {
                binding.timer.setText(String.format("Remaining : %d:%ss",l/60000,(""+(l%60000)).substring(0,2)));
            }
            @Override
            public void onFinish() {
                getActivity().getSharedPreferences("s",MODE_PRIVATE).edit().putBoolean("booked",false).apply();
            }
        };
        binding.text.setOnClickListener(v->{
            if(booked) {
                /*Intent intent = new Intent(getActivity(),QrActivity.class);
                startActivity(intent);*/
                Bundle bundle = new Bundle();
                bundle.putString("location",getArguments().getString("location")); // Put anything what you want
                bundle.putString("token",token+"");
                AfterBookingFragment fragment2 = new AfterBookingFragment();
                fragment2.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.news_fragment_container, fragment2)
                        .commit();
            }else {
                booked=true;
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setProgress(0);
                RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
                String url = "https://www.finalproject.xyz/vehicle_parking/api/bookings.php";
                progressDialog.show();
                token = System.currentTimeMillis();
                getActivity().getSharedPreferences("s",MODE_PRIVATE).edit().putString("token",token+"").apply();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        binding.text.setText("You have booked for a slot. Click to see QR code.");
                        getActivity().getSharedPreferences("s",MODE_PRIVATE).edit().putBoolean("booked",true).apply();
                        timer.start();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                getActivity().getSharedPreferences("s",MODE_PRIVATE).edit().putBoolean("booked",false).apply();
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return super.getHeaders();
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("user_id",userId);
                        params.put("time", getArguments().getString("location"));
                        params.put("token", String.valueOf(token));
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}
