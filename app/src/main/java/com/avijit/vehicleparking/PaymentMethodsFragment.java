package com.avijit.vehicleparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.avijit.vehicleparking.databinding.FragmentPaymentMethodsBinding;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 3:34 AM.
 * Email: avijitach@gmail.com.
 */
public class PaymentMethodsFragment extends Fragment {
    FragmentPaymentMethodsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPaymentMethodsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        View.OnClickListener p = v->{
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.news_fragment_container,new PaymentFragment()).commit();
        };
        binding.a.setOnClickListener(p);
        binding.b.setOnClickListener(p);
        binding.c.setOnClickListener(p);
    }
}
