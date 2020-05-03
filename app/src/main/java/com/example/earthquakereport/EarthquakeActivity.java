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

        CustomAdapter customAdapter = new CustomAdapter
                (this, R.layout.activity_earthquake, QueryUtils.extractEarthquakes());

        ListView listView =  (ListView) findViewById(R.id.earthquake_list);
        listView.setAdapter(customAdapter);
    }
}
