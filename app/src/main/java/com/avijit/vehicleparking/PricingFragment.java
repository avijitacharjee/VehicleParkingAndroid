package com.avijit.vehicleparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.FragmentPricingBinding;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 3:31 AM.
 * Email: avijitach@gmail.com.
 */
public class PricingFragment extends Fragment {
    FragmentPricingBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPricingBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
