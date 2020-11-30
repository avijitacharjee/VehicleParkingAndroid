package com.avijit.vehicleparking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Avijit Acharjee on 11/30/2020 at 9:21 PM.
 * Email: avijitach@gmail.com.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final String TAG = "MapsFragment";
    AutoCompleteTextView autocomplete;
    String[] arr = { "New Market", "Muradpur","Chawkbazar",
            "GEC", "Agrabad","Chandgao"};
    ArrayList<String>  strings = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        mapFragment.getMapAsync(this);
        return inflater.inflate(R.layout.fragment_maps,null,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autocomplete = (AutoCompleteTextView)
                view.findViewById(R.id.autoCompleteTextView1);
        strings.addAll(Arrays.asList(arr));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.select_dialog_item, arr);

        autocomplete.setThreshold(2);
        autocomplete.setAdapter(adapter);
        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: "+arr+" "+l+autocomplete.getText().toString());
                if(autocomplete.getText().toString().equals("New Market")){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.334792, 91.828059), 15.0f));
                }else if(autocomplete.getText().toString().equals("Muradpur")){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.367169, 91.827639), 15.0f));
                }else if(autocomplete.getText().toString().equals("Chawkbazar")){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.359015, 91.838439), 15.0f));
                }else if(autocomplete.getText().toString().equals("GEC")){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.358859, 91.821307), 15.0f));
                }else if(autocomplete.getText().toString().equals("Agrabad")){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.327096, 91.813046), 15.0f));
                }else if(autocomplete.getText().toString().equals("Chandgao")){
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.3269, 91.8162), 15.0f));
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(22.3721, 91.7930);
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.3591,91.8215)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.3269,91.8162)).title(""));
        //around market
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.334792, 91.828059)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.333585, 91.835221)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.333641, 91.836526)).title(""));
        //muradpur
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.367169, 91.827639)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.368726, 91.831477)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.364927, 91.830656)).title(""));

        //Chawkbazar
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.359015, 91.838439)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.356560, 91.840431)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.355853, 91.842177)).title(""));

        //GEC
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.358859, 91.821307)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.359523, 91.819880)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.358512, 91.821244)).title(""));

        // Agrabad
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.327096, 91.813046)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.324953, 91.810214)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.319633, 91.811587)).title(""));

        //Chadgao
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.324675, 91.810578)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.368726, 91.831477)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.364927, 91.830656)).title(""));

        //Andarkilla
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.377169, 91.827639)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.368926, 91.831077)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(22.354927, 91.839656)).title(""));

        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                try{
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/"+"22.3731, 91.7990/"+marker.getPosition().latitude+","+marker.getPosition().longitude);

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
                return true;
            }
        });




    }
}
