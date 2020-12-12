package com.avijit.vehicleparking;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

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
        if(A[0]+A[1]+A[2]+A[3]+A[4]+A[5]==6){
            binding.text.setText("No slots are available..");
            return;
        }
        CountDownTimer timer = new CountDownTimer(20*60*1000,1000) {
            @Override
            public void onTick(long l) {
                binding.timer.setText(String.format("Remaining : %d:%ss",l/60000,(""+(l%60000)).substring(0,2)));
            }
            @Override
            public void onFinish() {
                getActivity().getSharedPreferences("s",MODE_PRIVATE).edit().putBoolean("booked",false).apply();
                getActivity().getSharedPreferences("s", Context.MODE_PRIVATE)
                        .edit()
                        .putString("location","")
                        .apply();
            }
        };
        final int amount = ((int)(Math.random()*100)+10);
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
                SharedPreferences sf = getActivity().getSharedPreferences("s",MODE_PRIVATE);
                String msg = "Your selected location: " + getAddr(getArguments().getString("location"));
                msg += "\n\nYour current balance: "+sf.getInt("balance",0)+" Taka.\n\n";
                msg += "Cost for this parking: "+amount +" Taka.\n\n";
                msg += "Balance after payment: "+(sf.getInt("balance",0)-amount)+"\n\n";
                msg += "Proceed to make payment?";

                new AlertDialog
                        .Builder(getContext()).
                        setMessage(msg).
                        setPositiveButton("YES",(dialogInterface,i)->{
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
                                    sf.edit().putBoolean("booked",true).apply();
                                    sf.edit().putInt("balance",(sf.getInt("balance",0)- amount)).apply();
                                    timer.start();
                                }
                            },
                                    error -> {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("s",MODE_PRIVATE).edit().putBoolean("booked",false).apply();
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
                        }).
                        setNegativeButton("NO",null).
                        create().show();
                //requestQueue.add(stringRequest);
            }
        });
        ObjectAnimator.ofFloat(binding.getRoot(),View.ALPHA,0,1).setDuration(500).start();

    }
    private String getAddress(double latitude,double longitude){
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
            addresses = null;
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        return address;
    }
    private String getAddr(String s){
        String[] p = s.split(",");
        Double q[] = new Double[2];
        q[0] = Double.parseDouble(p[0]);
        q[1] = Double.parseDouble(p[1]);
        return getAddress(q[0],q[1]);
    }
}
