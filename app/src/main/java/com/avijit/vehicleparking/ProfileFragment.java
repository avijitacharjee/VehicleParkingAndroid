package com.avijit.vehicleparking;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.ActivityProfileBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 1:39 AM.
 * Email: avijitach@gmail.com.
 */
public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    ActivityProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Picasso.get().load("https://cdn.iconscout.com/icon/free/png-256/avatar-370-456322.png").into(binding.imageView);
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
        binding.nameTextView.setText("Name: "+name);
        binding.email.setText("Email: "+email);
        binding.phone.setText("Phone: "+phone);
    }
}
