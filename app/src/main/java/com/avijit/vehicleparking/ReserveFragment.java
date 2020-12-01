package com.avijit.vehicleparking;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.ActivityBookingBinding;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 3:17 AM.
 * Email: avijitach@gmail.com.
 */
public class ReserveFragment extends Fragment {
    private static final String TAG = "ReserveFragment";
    boolean booked;
    ActivityBookingBinding binding;
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
            email = user.getJSONObject("data").getString("email");
            phone = user.getJSONObject("data").getString("phone");
        }catch (Exception e){
            Log.d(TAG, "onCreate: "+e.toString());
        }
        CountDownTimer timer = new CountDownTimer(20*60*1000,1000) {
            @Override
            public void onTick(long l) {
                binding.timer.setText(String.format("Remaining : %d:%ss",l/60000,(""+(l%60000)).substring(0,2)));
            }

            @Override
            public void onFinish() {

            }
        };
        binding.text.setOnClickListener(v->{
            if(booked) {
                startActivity(new Intent(getContext(), QrActivity.class));
            }else {
                booked=true;
                binding.text.setText("You have booked for a slot. Click to see QR code.");
                timer.start();
            }
        });

    }
}
