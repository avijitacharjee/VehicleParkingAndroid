package com.avijit.vehicleparking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 12:50 AM.
 * Email: avijitach@gmail.com.
 */
public class HistoryFragment extends Fragment {
    private static final String TAG = "HistoryFragment";
    private ListView listView;
    private ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    class ResObj {
        String key, date, clockIn, clockOut, rate, user;
        ResObj() {
            key = date = clockIn = clockOut = rate = user = "";
        }

        // setter methods
        public void setKey(String key) {
            this.key = key;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setClockIn(String clockIn) {
            this.clockIn = clockIn;
        }

        public void setClockOut(String clockOut) {
            this.clockOut = clockOut;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        @Override
        public String toString() {
            return String.format("\n%s\n\tDATE: %s\n\tTIME: %s - %s\n\tRATE: %s\n\n",
                    key, date, clockIn, clockOut, rate);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_history,null,false);
    }
    public ArrayList<String> getData(){
        ArrayList<String> s = new ArrayList<>();
        ResObj obj = new ResObj();
        obj.setUser("Riad");
        obj.setRate("5");
        obj.setKey("1");
        obj.setClockIn("2.00pm");
        obj.setClockOut("3.00pm");
        obj.setDate("05/07/2020");
        s.add(obj.toString());
        obj.setKey("2");
        obj.setDate("10/06/2020");
        s.add(obj.toString());
        obj.setKey("3");
        obj.setClockIn("4.00pm");
        obj.setClockOut("6.00pm");
        obj.setDate("4/06/2020");
        s.add(obj.toString());
        obj.setKey("4");
        obj.setDate("3/06/2020");
        s.add(obj.toString());
        obj.setKey("5");
        obj.setDate("1/06/2020");
        s.add(obj.toString());
        obj.setKey("6");
        obj.setDate("10/05/2020");
        s.add(obj.toString());
        obj.setKey("7");
        obj.setDate("1/05/2020");
        s.add(obj.toString());

        return s;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.list_view);
        //list.addAll(getData());
        getOriginalData();
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
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
    private void getOriginalData(){
        String name="";
        String email="";
        String phone = "";
        String userId="";
        try {
            JSONObject user = new JSONObject(getActivity().getSharedPreferences("s",MODE_PRIVATE).getString("user",""));
            name = user.getJSONObject("data").getString("name");
            userId = user.getJSONObject("data").getString("id");
            email = user.getJSONObject("data").getString("email");
            phone = user.getJSONObject("data").getString("phone");
        }catch (Exception e){
            Log.d(TAG, "onCreate: "+e.toString());
        }
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://www.finalproject.xyz/vehicle_parking/api/get_bookings.php?user_id="+userId;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d(TAG, "onResponse: "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("data");

                    for(int i=0;i<data.length();i++){
                        String token =data.getJSONObject(i).getString("token");
                        String s = i+1+".\n\tDate : ";
                        s+=DateFormat.getDateTimeInstance().format(Double.parseDouble(token))+"\n\tLocation: ";

                        String[] p = data.getJSONObject(i).getString("time").split(",");
                        s+=getAddress(Double.parseDouble(p[0]),Double.parseDouble(p[1]))+"\n\n";
                        list.add(s);
                        Log.d(TAG, "onResponse: "+s);
                    }
                    arrayAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        requestQueue.add(stringRequest);
    }
}
