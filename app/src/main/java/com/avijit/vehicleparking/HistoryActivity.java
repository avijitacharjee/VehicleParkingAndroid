package com.avijit.vehicleparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> list = new ArrayList<>();
    private ResObj temp;

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
            return String.format("\n%s\t\t\t\t\t\t\tDATE: %s\n\tTIME: %s - %s\n\tRATE: %s\n\n",
                    key, date, clockIn, clockOut, rate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = (ListView) findViewById(R.id.list_view);
        list.addAll(getData());
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
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
        s.add(obj.toString());
        obj.setKey("3");
        s.add(obj.toString());
        return s;
    }

}