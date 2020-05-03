package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        ArrayList<Earthquake> arrayList = new ArrayList<>();
        arrayList.add(new Earthquake("Feb 2, 2016", 7.2, "San Fransisco"));
        arrayList.add(new Earthquake("July 20, 2015", 6.1, "London"));
        arrayList.add(new Earthquake("Nov 10, 2014", 3.9, "Tokyo"));
        arrayList.add(new Earthquake("May 3, 2014", 5.4, "Moscow"));
        arrayList.add(new Earthquake("Jan 31, 2013", 2.8, "Rio de Janiero"));
        arrayList.add(new Earthquake("Aug 19, 2012", 4.9, "Paris"));

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.activity_earthquake, arrayList);
        ListView listView =  (ListView) findViewById(R.id.earthquake_list);
        listView.setAdapter(customAdapter);

    }
}
