package com.avijit.vehicleparking;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.vehicleparking.databinding.FragmentAfterBookingBinding;

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Avijit Acharjee on 12/2/2020 at 3:32 AM.
 * Email: avijitach@gmail.com.
 */
public class AfterBookingFragment extends Fragment {
    FragmentAfterBookingBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAfterBookingBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String location;
        String token;
        try {
            location = getArguments().getString("location");
            token = getArguments().getString("token");
        }catch (Exception e){
            location = getActivity().getSharedPreferences("s", Context.MODE_PRIVATE).getString("location","");
            token = getActivity().getSharedPreferences("s",Context.MODE_PRIVATE).getString("token","abcd");
        }
        if(location.equals("")){
            binding.info.setText("You have no bookings.");
            binding.qr.setVisibility(View.GONE);
            binding.route.setVisibility(View.GONE);
            return;
        }
        binding.info.setText("Location: "+getAddr(location)+"\nDate: "+getDateTime(token));
        String finalLocation = location;
        binding.route.setOnClickListener(v->{
            try{
                Uri uri = Uri.parse("https://www.google.com/maps/dir/"+"22.3731, 91.7990/"+ finalLocation);

                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            catch (Exception e){
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        binding.qr.setOnClickListener(v->{
            startActivity(new Intent(getContext(),QrActivity.class));
        });
        ObjectAnimator.ofFloat(binding.getRoot(),View.ALPHA,0,1).setDuration(500).start();
    }
    private String getDateTime(String l){
        return DateFormat.getDateTimeInstance().format(Double.parseDouble(l));
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
    private Double[] getDoubles(String s ){
        String[] p = s.split(",");
        Double q[] = new Double[2];
        q[0] = Double.parseDouble(p[0]);
        q[1] = Double.parseDouble(p[1]);
        return q;
    }
}
