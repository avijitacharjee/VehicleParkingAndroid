package com.avijit.vehicleparking;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.FragmentContactBinding;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 2:32 AM.
 * Email: avijitach@gmail.com.
 */
public class ContactFragment extends Fragment {
    FragmentContactBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContactBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.call.setOnClickListener(v->{
            String phone = "+34666777888";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        });
        ObjectAnimator.ofFloat(binding.ll, View.ALPHA,0,1).setDuration(500).start();
    }
}
