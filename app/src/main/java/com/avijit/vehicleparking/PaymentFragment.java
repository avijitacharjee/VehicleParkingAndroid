package com.avijit.vehicleparking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.FragmentPaymentBinding;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 5:01 AM.
 * Email: avijitach@gmail.com.
 */
public class PaymentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentPaymentBinding.inflate(inflater,container,false).getRoot();
    }
}
