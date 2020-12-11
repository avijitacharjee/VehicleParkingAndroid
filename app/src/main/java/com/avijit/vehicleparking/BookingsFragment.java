package com.avijit.vehicleparking;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.FragmentBookingsBinding;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 12:34 PM.
 * Email: avijitach@gmail.com.
 */
public class BookingsFragment extends Fragment {
    FragmentBookingsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookingsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if(getActivity().getSharedPreferences("s", Context.MODE_PRIVATE).getBoolean("booked",false)){
            binding.qr.setOnClickListener(v->startActivity(new Intent(getContext(),QrActivity.class)));
        }else {
            binding.qr.setText("You have not booked for any parking");
        }
        ObjectAnimator.ofFloat(binding.getRoot(),View.ALPHA,0,1).setDuration(500).start();

    }
}
