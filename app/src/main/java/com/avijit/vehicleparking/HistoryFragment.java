package com.avijit.vehicleparking;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Avijit Acharjee on 12/1/2020 at 12:50 AM.
 * Email: avijitach@gmail.com.
 */
public class HistoryFragment extends Fragment {
    private ListView listView;
    private ArrayList<String> list = new ArrayList<>();

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
        list.addAll(getData());
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list)
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
}
