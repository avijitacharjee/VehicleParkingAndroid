package com.avijit.vehicleparking;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.ActivityHelpBinding;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 2:04 AM.
 * Email: avijitach@gmail.com.
 */
public class HelpFragment extends Fragment {
    ActivityHelpBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityHelpBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ObjectAnimator.ofFloat(binding.activityHelpUser,View.ALPHA,0,1).setDuration(500).start();
    }
}
